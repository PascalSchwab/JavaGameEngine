package de.pascalschwab.networking;

import de.pascalschwab.networking.messages.ClientDisconnectedMessage;
import de.pascalschwab.networking.messages.NetworkMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public abstract class NetworkServer implements NetworkObject {
    private final List<ClientSocket> clients = new ArrayList<>();
    private final ServerSocket socket;
    public NetworkServer(int port){
        try {
            this.socket = new ServerSocket(port);
        }
        catch (IOException e){
            throw new RuntimeException("Can't create server socket");
        }
    }

    public final void start(){
        while(true) {
            try {
                Socket client = socket.accept();
                System.out.println("New client connected: " + client.getInetAddress());
                ClientSocket clientSocket = new ClientSocket(client);
                clients.add(clientSocket);

                HandleClientThread handleThread = new HandleClientThread(this, clientSocket);
                handleThread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected abstract void onMessageArrived(ClientSocket socket, NetworkMessage message);

    @Override
    public final void handleMessageArrived(ClientSocket sender, NetworkMessage message) {
        switch (message.getRequestType()){
            case CLIENT_DISCONNECT:
                handleClientDisconnected(sender);
                break;
            default:
                onMessageArrived(sender, message);
                break;
        }
    }

    @Override
    public final void send(ClientSocket sender, NetworkMessage message){
        switch (message.getSendType()){
            case ALL:
                for(ClientSocket receiver : clients){
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
                for(ClientSocket receiver : clients){
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

    protected abstract void onClientDisconnected(ClientSocket client);

    private void handleClientDisconnected(ClientSocket client){
        send(client, new ClientDisconnectedMessage(clients.indexOf(client)));
        onClientDisconnected(client);
        clients.remove(client);
        client.dispose();
    }
}
