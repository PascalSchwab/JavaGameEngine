package de.pascalschwab.managers;

import de.pascalschwab.gameobjects.GameObject;
import de.pascalschwab.gameobjects.RenderObject;

public class DevTools {
    private static boolean active;

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
