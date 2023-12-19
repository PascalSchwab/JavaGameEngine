import de.pascalschwab.networking.ClientSocket;
import de.pascalschwab.networking.NetworkClient;
import de.pascalschwab.networking.messages.ClientConnectedMessage;
import de.pascalschwab.networking.messages.ClientDisconnectedMessage;
import de.pascalschwab.networking.messages.NetworkMessage;
import org.joml.Vector3f;

public class Client extends NetworkClient {
    @Override
    protected void onMessageArrived(ClientSocket sender, NetworkMessage message) {
        if(message instanceof PlayerPosMessage){
            PlayerPosMessage playerPos = (PlayerPosMessage) message;
            System.out.println(playerPos.getObject());
        }
    }

    @Override
    protected void onClientDisconnected(ClientDisconnectedMessage message) {

    }

    @Override
    protected void onClientConnected(ClientConnectedMessage message) {

    }
}
