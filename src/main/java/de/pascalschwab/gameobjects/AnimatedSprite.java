package de.pascalschwab.gameobjects;

import de.pascalschwab.rendering.texture.Animation;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class AnimatedSprite extends Sprite {
    private Animation currentAnimation = null;
    private List<Animation> animations = new ArrayList<>();
    private float animationTime = 0;

    public AnimatedSprite(Window window, GameObject parent, Vector2f position, Vector2f size, float zIndex, String texturePath) {
        super(window, parent, position, size, zIndex, texturePath);
    }

    public void addAnimation(Animation animation) {
        animations.add(animation);
    }

    public void playCurrentAnimation(float deltaTime) {
        animationTime += deltaTime;
        if (animationTime >= currentAnimation.getFrameRate()) {
            animationTime = 0;
            updateUVS(currentAnimation.getNextFrame());
        }
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public void setCurrentAnimation(String name) {
        if (currentAnimation == null || !currentAnimation.getName().equals(name)) {
            currentAnimation = animations.stream().filter(a -> a.getName().equals(name)).findFirst().get();
            updateUVS(currentAnimation.getCurrentFrame());
        }
    }
}
