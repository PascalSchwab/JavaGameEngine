package de.pascalschwab.networking;

import de.pascalschwab.networking.messages.NetworkMessage;

import java.io.IOException;

public class HandleClientThread extends Thread{
    private final ClientSocket sender;
    private final NetworkObject object;
    public HandleClientThread(NetworkObject object, ClientSocket sender){
        this.sender = sender;
        this.object = object;
    }

    @Override
    public void run(){
        while(!sender.isClosed()){
            try{
                NetworkMessage message = (NetworkMessage) sender.getIn().readObject();
                object.handleMessage(sender, message);
            }
            catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
