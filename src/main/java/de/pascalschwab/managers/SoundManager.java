package de.pascalschwab.managers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.pascalschwab.sound.SoundBuffer;
import de.pascalschwab.sound.SoundSource;
import org.joml.Vector3f;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class SoundManager {
    private final long device;
    private final long context;
    private static final Map<String, SoundBuffer> sounds = new HashMap<>();
    public SoundManager(){
        device = alcOpenDevice((ByteBuffer) null);
        if (device == NULL) {
            throw new IllegalStateException("Failed to open the default OpenAL device.");
        }
        ALCCapabilities deviceCaps = ALC.createCapabilities(device);
        context = alcCreateContext(device, (IntBuffer) null);
        if (context == NULL) {
            throw new IllegalStateException("Failed to create OpenAL context.");
        }
        alcMakeContextCurrent(context);
        AL.createCapabilities(deviceCaps);
    }

    public void addSound(String soundName, String path){
        sounds.put(soundName, new SoundBuffer(path));
    }

    public static void loadSoundsFromJson(String path){
        try {
            String jsonText = FileManager.getTextFromFile(path);
            JsonArray jsonSounds = JsonParser.parseString(jsonText).getAsJsonArray();
            jsonSounds.forEach(object -> {
                JsonObject sound = object.getAsJsonObject();
                sounds.put(sound.get("name").getAsString(), new SoundBuffer(sound.get("path").getAsString()));
            });
        }
        catch (Exception e) {
            throw new RuntimeException("Json File from the path: " + path + " cannot been loaded.");
        }
    }

    public void dispose(){
        sounds.values().forEach(SoundBuffer::dispose);
        if (context != NULL) {
            alcDestroyContext(context);
        }
        if (device != NULL) {
            alcCloseDevice(device);
        }
    }

    public static Map<String, SoundBuffer> getSounds() {
        return sounds;
    }
}
