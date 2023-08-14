package de.pascalschwab.standard.lists;

import de.pascalschwab.gameobjects.GameObject;

import java.util.ArrayList;
import java.util.Comparator;

public final class LayerBasedList<T extends GameObject> extends ArrayList<T> {
    @Override
    public boolean add(T object) {
        super.add(object);
        this.sort(Comparator.comparing(GameObject::getZIndex));
        return true;
    }
}
