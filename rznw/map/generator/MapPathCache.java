package rznw.map.generator;

import java.util.HashSet;

public class MapPathCache
{
    private HashSet<String> usedPoints;

    public MapPathCache()
    {
        this.usedPoints = new HashSet<String>();
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
