package de.engine.networking.messages;

import de.engine.networking.RequestType;
import de.engine.networking.SendType;

import java.io.Serializable;

public abstract class NetworkMessage implements Serializable {
    private final RequestType requestType;
    private final SendType sendType;
    private final Object object;
    public NetworkMessage(SendType sendType, Object object){
        this(RequestType.STANDARD, sendType, object);
    }
    public NetworkMessage(RequestType requestType, SendType sendType, Object object){
        this.requestType = requestType;
        this.sendType = sendType;
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public SendType getSendType() {
        return sendType;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
