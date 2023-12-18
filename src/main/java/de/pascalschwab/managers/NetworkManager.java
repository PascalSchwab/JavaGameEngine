package de.pascalschwab.managers;

import de.pascalschwab.networking.ClientRecieveThread;
import de.pascalschwab.networking.NetworkClient;
import de.pascalschwab.networking.messages.NetworkMessage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class NetworkManager {
    private NetworkClient client;
    public void connect(String address, int port) {
        try{
            Socket socket = new Socket(InetAddress.getByName(address), port);
            client = new NetworkClient(socket);
        }
        catch (IOException e){
            throw new RuntimeException("Can't connect to the server " + address);
        }

        ClientRecieveThread clientRecieveThread = new ClientRecieveThread(this);
        clientRecieveThread.start();
    }
    public void send(NetworkMessage request) {
        if(isConnected()){
            try{
                client.getOut().writeObject(request);
            }
            catch (IOException e){
                System.err.println("Can't send data to server");
            }
        }
        else{
            System.err.println("You are not connected to the server");
        }
    }

    public void standardHandleMessage(NetworkMessage message) {
        System.out.println(message.getRequestType());
    }
    public void dispose() {
        client.dispose();
    }
    public boolean isConnected(){
        return !client.isClosed();
    }

    public NetworkClient getClient() {
        return client;
    }
}
