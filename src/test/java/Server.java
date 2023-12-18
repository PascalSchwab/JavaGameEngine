import de.pascalschwab.networking.NetworkClient;
import de.pascalschwab.networking.NetworkServer;
import de.pascalschwab.networking.messages.NetworkMessage;

public class Server extends NetworkServer {
    private static final int PORT = 8000;
    public Server() {
        super(PORT);
    }
    @Override
    protected void handleMessage(NetworkClient sender, NetworkMessage message){

    }
    public static void main(String[] args){
        Server server = new Server();
        server.start();
    }
}
