package de.engine.rendering.texture;

import org.joml.Vector2f;

public final class Animation {
    private final Vector2f[] frames;
    private final int frameRate;
    private final String name;
    private int currentPosition = 0;

    public Animation(String name, Vector2f[] frames, int frameRate) {
        this.name = name;
        this.frames = frames;
        this.frameRate = frameRate;
    }

    public Vector2f getCurrentFrame() {
        return frames[currentPosition];
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int position) {
        this.currentPosition = position;
    }

    public Vector2f getNextFrame() {
        currentPosition = (currentPosition + 1 >= frames.length) ? 0 : currentPosition + 1;
        return getCurrentFrame();
    }

    public int getFrameRate() {
        return frameRate;
    }

    public String getName() {
        return name;
    }
}
