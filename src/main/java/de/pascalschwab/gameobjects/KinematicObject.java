package de.pascalschwab.gameobjects;

import de.pascalschwab.window.Window;
import org.joml.Vector2f;

public abstract class KinematicObject extends GameObject {
    /*private static final double DEFAULT_MOVE_SMOOTHNESS = 7;
    public boolean gravity = true;
    private Vector2 targetMovementPosition = new Vector2(this.getPosition().x, this.getPosition().y);*/
    public KinematicObject(Window window, GameObject parent, Vector2f position, Vector2f size, int zIndex) {
        super(window, parent, position, size, zIndex);
    }

    public final void tick() {
        // gravity();
        // moveToTargetPosition();
        update();
    }

    /*private void moveToTargetPosition() {
        if (!targetMovementPosition.equals(this.getPosition())) {
            Vector2 direction = this.targetMovementPosition.subtract(this.getPosition()).normalize();
            Vector2 newPosition = this.getPosition().add(direction.multiScalar(DEFAULT_MOVE_SMOOTHNESS));
            if (this.targetMovementPosition.subtract(this.getPosition()).getLength() <= this.targetMovementPosition.subtract(newPosition).getLength()) {
                this.setPosition(this.targetMovementPosition);
            } else {
                this.setPosition(newPosition);
            }
        }
    }*/

    protected abstract void update();

    /*private void gravity() {
        moveTowards(new Vector2(0, 1));
    }

    public final void moveTowards(Vector2 direction) {
        targetMovementPosition = this.getPosition().add(direction);
    }*/
}
