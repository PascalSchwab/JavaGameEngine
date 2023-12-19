import de.pascalschwab.networking.ClientSocket;
import de.pascalschwab.networking.NetworkServer;
import de.pascalschwab.networking.messages.NetworkMessage;

public class Server extends NetworkServer {
    public Server(int port) {
        super(port);
    }

    @Override
    protected void onMessageArrived(ClientSocket sender, NetworkMessage message){
        if(message instanceof PlayerPosMessage){
            send(null, message);
        }
    }

    @Override
    protected void onClientDisconnected(ClientSocket client) {

    }

    public static void main(String[] args){
        Server server = new Server(8000);
        server.start();
    }
}
