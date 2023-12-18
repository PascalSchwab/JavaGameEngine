package de.pascalschwab.networking;

import de.pascalschwab.networking.messages.ClientDisconnectedMessage;
import de.pascalschwab.networking.messages.NetworkMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public abstract class NetworkServer {
    private final List<NetworkClient> clients = new ArrayList<>();
    private final ServerSocket socket;
    private boolean isRunning = false;
    public NetworkServer(int port){
        try {
            this.socket = new ServerSocket(port);
        }
        catch (IOException e){
            throw new RuntimeException("Can't create server socket");
        }
    }

    public final void start(){
        isRunning = true;

        while(isRunning) {
            try {
                Socket client = socket.accept();
                System.out.println("New client connected: " + client.getInetAddress());
                NetworkClient networkClient = new NetworkClient(client);
                clients.add(networkClient);

                ServerClientThread serverClientThread = new ServerClientThread(this, networkClient);
                serverClientThread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public final void basicHandleMessage(NetworkClient sender, NetworkMessage message){
        switch (message.getRequestType()){
            case CLIENT_CONNECT:
                break;
            case CLIENT_DISCONNECT:
                clientDisconnected(sender);
                break;
            case UPDATE_VARIABLE:
                handleMessage(sender, message);
                break;
        }
    }

    protected abstract void handleMessage(NetworkClient sender, NetworkMessage message);

    protected final void send(NetworkClient sender, NetworkMessage message){
        switch (message.getSendType()){
            case ALL:
                for(NetworkClient receiver : clients){
                    try{
                        receiver.getOut().writeObject(message);
                        receiver.getOut().flush();
                    }
                    catch (IOException e){
                        System.err.println("Can't send data to client");
                    }
                }
                break;
            case OTHERS:
                for(NetworkClient receiver : clients){
                    if(!receiver.equals(sender)){
                        try{
                            receiver.getOut().writeObject(message);
                            receiver.getOut().flush();
                        }
                        catch (IOException e){
                            System.err.println("Can't send data to client");
                        }
                    }
                }
                break;
        }
    }

    protected void clientDisconnected(NetworkClient sender){
        send(null, new ClientDisconnectedMessage(clients.indexOf(sender)));
        clients.remove(sender);
        sender.dispose();
    }
}
