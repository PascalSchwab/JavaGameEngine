package de.pascalschwab.gameobjects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.pascalschwab.managers.FileManager;
import de.pascalschwab.rendering.texture.Animation;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class AnimatedSprite extends Sprite {
    private final List<Animation> animations = new ArrayList<>();
    private Animation currentAnimation = null;
    private float animationTime = 0;

    public AnimatedSprite(Window window, GameObject parent, Vector3f position, Vector2f size, String texturePath, Vector2f frameSize) {
        super(window, parent, position, size, texturePath, frameSize);
    }

    public void addAnimation(Animation animation) {
        animations.add(animation);
    }

    public void addAnimationsFromJson(String path) {
        try {
            String jsonText = FileManager.getTextFromFile(path);
            JsonArray jsonData = JsonParser.parseString(jsonText).getAsJsonArray();
            jsonData.forEach((object) -> {
                JsonObject jsonAnimation = object.getAsJsonObject();
                JsonArray jsonFrames = jsonAnimation.getAsJsonArray("frames");
                Vector2f[] frames = new Vector2f[jsonFrames.size()];
                for (int i = 0; i < jsonFrames.size(); i++) {
                    JsonObject jsonFrame = jsonFrames.get(i).getAsJsonObject();
                    frames[i] = new Vector2f(jsonFrame.get("x").getAsFloat(), jsonFrame.get("y").getAsFloat());
                }
                Animation animation = new Animation(jsonAnimation.get("name").getAsString(),
                        frames, jsonAnimation.get("frameRate").getAsInt());
                animations.add(animation);
            });
        } catch (Exception e) {
            throw new RuntimeException("Json File from the path: " + path + " cannot been loaded.");
        }
    }

    public void playAnimation(String name, float deltaTime) {
        if (!currentAnimation.getName().equals(name)) {
            setCurrentAnimation(name);
        }
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

    public void setCurrentAnimationPosition(String name, int position) {
        if (!currentAnimation.getName().equals(name)) {
            setCurrentAnimation(name);
        }
        if (getCurrentAnimation().getCurrentPosition() != position) {
            getCurrentAnimation().setCurrentPosition(position);
            updateUVS(currentAnimation.getCurrentFrame());
        }
    }
}
