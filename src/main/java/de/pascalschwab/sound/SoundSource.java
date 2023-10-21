package de.pascalschwab.sound;

import de.pascalschwab.gameobjects.GameObject;
import de.pascalschwab.managers.SoundManager;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.openal.AL10.*;

public final class SoundSource extends GameObject {
    private final int id;
    private boolean relative;
    private boolean looped;

    public SoundSource() {
        this(null, new Vector3f(0, 0, 0));
    }

    public SoundSource(GameObject parent) {
        this(parent, parent.getPosition());
    }

    public SoundSource(GameObject parent, Vector3f position) {
        super(parent, position, new Vector2f(0, 0));
        this.id = alGenSources();
        this.looped = false;
        this.relative = true;
    }

    @Override
    public void addPosition(Vector3f position) {
        super.addPosition(position);
        this.setPosition(position);
    }

    public int getSourceId() {
        return id;
    }

    public boolean isLooped() {
        return looped;
    }

    public void setLooped(boolean looped) {
        this.looped = looped;
        alSourcei(id, AL_LOOPING, looped ? AL_TRUE : AL_FALSE);
    }

    public boolean isRelative() {
        return relative;
    }

    public void setRelative(boolean relative) {
        this.relative = relative;
        alSourcei(id, AL_SOURCE_RELATIVE, relative ? AL_TRUE : AL_FALSE);
    }

    private void setBuffer(int bufferID) {
        stop();
        alSourcei(id, AL_BUFFER, bufferID);
    }

    private void setPosition(Vector3f position) {
        alSource3f(id, AL_POSITION, position.x, position.y, position.z);
    }

    public void setSpeed(Vector3f speed) {
        alSource3f(id, AL_VELOCITY, speed.x, speed.y, speed.z);
    }

    public void setGain(float gain) {
        alSourcef(id, AL_GAIN, gain);
    }

    private void setProperty(int param, float value) {
        alSourcef(id, param, value);
    }

    public void play(String soundName) {
        play(soundName, false, true);
    }

    public void play(String soundName, boolean looped) {
        play(soundName, looped, true);
    }

    public void play(String soundName, boolean looped, boolean relative) {
        if (!this.isPlaying()) {
            this.setRelative(relative);
            this.setLooped(looped);
            this.setBuffer(SoundManager.getSounds().get(soundName).getId());
            alSourcePlay(id);
        }
    }


    public boolean isPlaying() {
        return alGetSourcei(id, AL_SOURCE_STATE) == AL_PLAYING;
    }

    public boolean isPaused() {
        return alGetSourcei(id, AL_SOURCE_STATE) == AL_PAUSED;
    }

    public boolean isStopped() {
        return alGetSourcei(id, AL_SOURCE_STATE) == AL_STOPPED;
    }

    public void pause() {
        alSourcePause(id);
    }

    public void stop() {
        alSourceStop(id);
    }

    @Override
    public void dispose() {
        stop();
        alDeleteSources(id);
    }
}
