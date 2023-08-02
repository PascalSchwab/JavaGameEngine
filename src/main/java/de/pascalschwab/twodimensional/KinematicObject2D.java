package de.pascalschwab.twodimensional;

import de.pascalschwab.standard.GameObject;
import de.pascalschwab.standard.KinematicObject;
import de.pascalschwab.standard.math.Vector2;
import de.pascalschwab.window.Window;

public abstract class KinematicObject2D extends KinematicObject<Vector2> {
    /*private static final double DEFAULT_MOVE_SMOOTHNESS = 7;
    public boolean gravity = true;
    private Vector2 targetMovementPosition = new Vector2(this.getPosition().x, this.getPosition().y);*/

    public KinematicObject2D(Window<Vector2> window, GameObject<Vector2> parent, Vector2 position, Vector2 size, int zIndex) {
        super(window, parent, position, size, zIndex);
    }

    public KinematicObject2D(Window<Vector2> window, Vector2 position, Vector2 size, int zIndex) {
        this(window, null, position, size, zIndex);
    }

    @Override
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
