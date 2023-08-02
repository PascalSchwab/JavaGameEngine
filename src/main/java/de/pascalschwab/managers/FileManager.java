package de.pascalschwab.managers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileManager {
    public static String getTextFromFile(String path) throws IOException {
        return new String(Files.readAllBytes(Path.of(path)));
    }
}
