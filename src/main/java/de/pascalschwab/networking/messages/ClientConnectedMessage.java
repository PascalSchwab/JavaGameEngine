package de.pascalschwab.networking.messages;

import de.pascalschwab.networking.RequestType;
import de.pascalschwab.networking.SendType;

public final class ClientConnectedMessage extends NetworkMessage {
    public ClientConnectedMessage(int clientID) {
        super(RequestType.CLIENT_CONNECTED, SendType.ALL, clientID);
    }
}
