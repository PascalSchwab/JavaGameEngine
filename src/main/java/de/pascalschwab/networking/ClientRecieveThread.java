package de.pascalschwab.networking;

import de.pascalschwab.managers.NetworkManager;
import de.pascalschwab.networking.messages.NetworkMessage;

import java.io.IOException;

public class ClientRecieveThread extends Thread{
    private final NetworkManager networkManager;
    public ClientRecieveThread(NetworkManager networkManager){
        this.networkManager = networkManager;
    }
    @Override
    public void run(){
        try {
            while(!networkManager.getClient().isClosed()){
                NetworkMessage message = (NetworkMessage) networkManager.getClient().getIn().readObject();
                networkManager.standardHandleMessage(message);
            }
        }
        catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
