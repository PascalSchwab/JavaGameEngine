import de.pascalschwab.networking.RequestType;
import de.pascalschwab.networking.SendType;
import de.pascalschwab.networking.messages.NetworkMessage;

public class PlayerPosMessage extends NetworkMessage {
    public PlayerPosMessage(Object object) {
        super(RequestType.UPDATE, SendType.ALL, object);
    }
}
