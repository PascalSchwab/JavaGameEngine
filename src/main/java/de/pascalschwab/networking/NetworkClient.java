package de.pascalschwab.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkClient {
    private final Socket socket;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;

    public NetworkClient(Socket socket){
        this.socket = socket;
    }

    public boolean isClosed(){
        return socket.isClosed();
    }

    public ObjectInputStream getIn() {
        if(this.in == null){
            try {
                in = new ObjectInputStream(socket.getInputStream());
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return in;
    }

    public ObjectOutputStream getOut() {
        if(this.out == null){
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                out.flush();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return out;
    }

    public void dispose(){
        try{
            this.socket.close();
            this.getIn().close();
            this.getOut().close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
