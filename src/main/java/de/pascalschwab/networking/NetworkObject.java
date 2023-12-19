package de.pascalschwab.networking;

import de.pascalschwab.networking.messages.NetworkMessage;

public interface NetworkObject {
    void send(ClientSocket sender, NetworkMessage message);
    void handleMessageArrived(ClientSocket sender, NetworkMessage message);
}
