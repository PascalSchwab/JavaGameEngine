package de.pascalschwab.networking;

import de.pascalschwab.networking.messages.NetworkMessage;

import java.io.IOException;

public final class ServerClientThread extends Thread{
    private final NetworkServer server;
    private final NetworkClient client;
    public ServerClientThread(NetworkServer server, NetworkClient client){
        this.client = client;
        this.server = server;
    }
    @Override
    public void run(){
        while (!client.isClosed()) {
            try {
                NetworkMessage message = (NetworkMessage) client.getIn().readObject();
                System.out.println(message.getRequestType());
                server.basicHandleMessage(client, message);
            }
            catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
