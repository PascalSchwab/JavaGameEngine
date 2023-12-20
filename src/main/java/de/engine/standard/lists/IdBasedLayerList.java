package de.engine.standard.lists;

import de.engine.tilemap.Layer;

import java.util.ArrayList;
import java.util.Comparator;

public class IdBasedLayerList<T extends Layer> extends ArrayList<T> {
    @Override
    public boolean add(T object) {
        super.add(object);
        this.sort(Comparator.comparing(Layer::getId));
        return true;
    }
}
