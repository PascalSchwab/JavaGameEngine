package de.pascalschwab.networking.messages;

import de.pascalschwab.networking.NetworkClient;
import de.pascalschwab.networking.RequestType;
import de.pascalschwab.networking.SendType;

public final class ClientDisconnectedMessage extends NetworkMessage {
    public ClientDisconnectedMessage(int clientID) {
        super(RequestType.CLIENT_DISCONNECTED, SendType.ALL, clientID);
    }
}
