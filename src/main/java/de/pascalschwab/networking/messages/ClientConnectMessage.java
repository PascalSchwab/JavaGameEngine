package de.pascalschwab.networking.messages;

import de.pascalschwab.networking.RequestType;
import de.pascalschwab.networking.SendType;

public final class ClientConnectMessage extends NetworkMessage {
    public ClientConnectMessage() {
        super(RequestType.CLIENT_CONNECT, SendType.SERVER, null);
    }
}
