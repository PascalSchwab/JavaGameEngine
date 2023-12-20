package de.engine.networking.messages;

import de.engine.networking.RequestType;
import de.engine.networking.SendType;

public final class ClientDisconnectMessage extends NetworkMessage {
    public ClientDisconnectMessage() {
        super(RequestType.CLIENT_DISCONNECT, SendType.SERVER, null);
    }
}
