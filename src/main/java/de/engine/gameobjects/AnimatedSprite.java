package de.engine.gameobjects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.engine.geometry.TextureRectangle;
import de.engine.managers.FileManager;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public final class AnimatedSprite extends TextureRectangle {
    private final List<Animation> animations = new ArrayList<>();
    private Animation currentAnimation = null;
    private float animationTime = 0;

    public AnimatedSprite(GameObject parent, String texturePath, Vector2f frameSize){
        this(parent, parent.getPosition(), parent.getSize(), texturePath, frameSize, null);
    }

    public AnimatedSprite(GameObject parent, String texturePath, Vector2f frameSize, String animationPath){
        this(parent, parent.getPosition(), parent.getSize(), texturePath, frameSize, animationPath);
    }

    public AnimatedSprite(GameObject parent, Vector3f position, Vector2f size, String texturePath,
                          Vector2f frameSize, String animationPath) {
        super(parent, position, size, texturePath, frameSize);
        if(animationPath != null){
            addAnimationsFromJson(animationPath);
        }
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
