package de.pascalschwab.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket {
    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientSocket(Socket socket){
        this.socket = socket;
    }

    public boolean isClosed(){
        return socket.isClosed();
    }

    public ObjectInputStream getIn() {
        if(in == null){
            try{
                in = new ObjectInputStream(socket.getInputStream());
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return in;
    }

    public ObjectOutputStream getOut() {
        if(out == null){
            try{
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
            this.in.close();
            this.out.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
