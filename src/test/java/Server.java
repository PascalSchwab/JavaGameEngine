import de.pascalschwab.networking.ClientSocket;
import de.pascalschwab.networking.NetworkServer;
import de.pascalschwab.networking.messages.ClientDisconnectedMessage;
import de.pascalschwab.networking.messages.NetworkMessage;

public class Server extends NetworkServer {
    private static final int PORT = 8000;
    public Server() {
        super(PORT);
    }
    @Override
    protected void handleOwnMessage(ClientSocket sender, NetworkMessage message){
        if(message instanceof PlayerPosMessage){
            send(null, message);
        }
    }
    public static void main(String[] args){
        Server server = new Server();
        server.start();
    }
}
