package de.pascalschwab.networking.messages;

import de.pascalschwab.networking.RequestType;
import de.pascalschwab.networking.SendType;

public final class ClientDisconnectMessage extends NetworkMessage {
    public ClientDisconnectMessage() {
        super(RequestType.CLIENT_DISCONNECT, SendType.SERVER, null);
    }
}
