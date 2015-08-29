package rznw.game;

import rznw.map.element.MapElement;

public abstract class Character
{
    MapElement mapElement;

    public MapElement getMapElement()
    {
        return this.mapElement;
    }

    public abstract void generateMapElement(int row, int column);
}
