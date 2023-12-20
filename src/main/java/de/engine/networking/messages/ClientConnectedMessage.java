package de.engine.networking.messages;

import de.engine.networking.RequestType;
import de.engine.networking.SendType;

public final class ClientConnectedMessage extends NetworkMessage {
    public ClientConnectedMessage(int clientID) {
        super(RequestType.CLIENT_CONNECTED, SendType.ALL, clientID);
    }
}
