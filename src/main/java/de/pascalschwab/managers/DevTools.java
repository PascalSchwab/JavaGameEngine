package de.pascalschwab.managers;

import de.pascalschwab.gameobjects.GameObject;
import de.pascalschwab.gameobjects.RenderObject;

public final class DevTools {
    public static int fps = 0;
    private static boolean active = false;

    public static boolean isActive() {
        return DevTools.active;
    }

    public static void setActive(boolean active) {
        DevTools.active = active;

        for (GameObject object : WindowManager.getWindow().collisionObjects) {
            RenderObject renderObject = (RenderObject) object;
            renderObject.setVisible(active);
        }
    }
}
