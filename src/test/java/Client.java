import de.pascalschwab.networking.ClientSocket;
import de.pascalschwab.networking.NetworkClient;
import de.pascalschwab.networking.messages.NetworkMessage;
import org.joml.Vector3f;

public class Client extends NetworkClient {
    @Override
    protected void handleOwnMessage(ClientSocket sender, NetworkMessage message) {
        if(message instanceof PlayerPosMessage){
            System.out.println("New player position: " + message.getObject());
        }
    }
}
