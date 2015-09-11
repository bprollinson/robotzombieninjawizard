package rznw.map.generator;

import rznw.map.generator.direction.PathDirection;

public class MapPath implements Cloneable
{
    private MapPoint startingPoint;
    private MapPoint currentPoint;

    public MapPath(MapPoint startingPoint)
    {
        this.startingPoint = startingPoint;
        this.currentPoint = startingPoint;
    }

    public MapPath(MapPoint startingPoint, MapPoint currentPoint)
    {
        this.startingPoint = startingPoint;
        this.currentPoint = currentPoint;
    }

    public MapPoint getCurrentPoint()
    {
        return this.currentPoint;
    }

    public MapPath getSubsequentPath(PathDirection direction)
    {
        MapPath newPath = (MapPath)this.clone();
        newPath.addPathDirection(direction);

        return newPath;
    }

    public MapPath clone()
    {
        MapPoint newStartingPoint = this.startingPoint.clone();
        MapPoint newCurrentPoint = this.currentPoint.clone();
        MapPath newPath = new MapPath(newStartingPoint, newCurrentPoint);

        return newPath;
    }

    public void addPathDirection(PathDirection direction)
    {
        this.currentPoint = new MapPoint(this.currentPoint.getX() + direction.getDeltaX(), this.currentPoint.getY() + direction.getDeltaY());
    }
}
