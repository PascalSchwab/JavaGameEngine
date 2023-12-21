package de.engine.networking.messages;

import de.engine.networking.RequestType;
import de.engine.networking.SendType;

public final class ClientDisconnectedMessage extends NetworkMessage {
    public ClientDisconnectedMessage(int clientID) {
        super(RequestType.CLIENT_DISCONNECTED, SendType.ALL, clientID);
    }
}
