package de.engine.networking;

import de.engine.networking.messages.NetworkMessage;

public interface NetworkObject {
    void send(ClientSocket sender, NetworkMessage message);
    void handleMessageArrived(ClientSocket sender, NetworkMessage message);
}
