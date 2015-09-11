package rznw.map.generator;

import java.util.HashSet;

public class MapPathCache
{
    private HashSet usedPoints;

    public MapPathCache()
    {
        this.usedPoints = new HashSet();
    }

    public void registerPathAsUsed(MapPath path)
    {
        this.usedPoints.add(path.getCurrentPoint().toString());
    }

    public boolean pathIsUsed(MapPath path)
    {
        return this.usedPoints.contains(path.getCurrentPoint().toString());
    }
}
