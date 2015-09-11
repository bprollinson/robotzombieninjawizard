package rznw.map.generator;

import rznw.map.generator.direction.PathDirection;

import java.util.Vector;

public class MapPath implements Cloneable
{
    private MapPoint startingPoint;
    private MapPoint currentPoint;
    private Vector<PathDirection> path;

    public MapPath(MapPoint startingPoint)
    {
        this.startingPoint = startingPoint;
        this.currentPoint = startingPoint;
        this.path = new Vector<PathDirection>();
    }

    public MapPath(MapPoint startingPoint, MapPoint currentPoint, Vector<PathDirection> path)
    {
        this.startingPoint = startingPoint;
        this.currentPoint = currentPoint;
        this.path = path;
    }

    public MapPoint getCurrentPoint()
    {
        return this.currentPoint;
    }

    public MapPoint[] getPoints()
    {
        MapPoint[] result = new MapPoint[path.size() + 1];

        MapPoint currentPoint = this.startingPoint;
        result[0] = startingPoint;

        for (int i = 0; i < this.path.size(); i++)
        {
            PathDirection direction = this.path.get(i);

            currentPoint = new MapPoint(currentPoint.getX() + direction.getDeltaX(), currentPoint.getY() + direction.getDeltaY());
            result[i + 1] = currentPoint;
        }

        return result;
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
        Vector<PathDirection> pathVector = (Vector<PathDirection>)this.path.clone();
        MapPath newPath = new MapPath(newStartingPoint, newCurrentPoint, pathVector);

        return newPath;
    }

    public void addPathDirection(PathDirection direction)
    {
        this.currentPoint = new MapPoint(this.currentPoint.getX() + direction.getDeltaX(), this.currentPoint.getY() + direction.getDeltaY());
        this.path.add(direction);
    }

    public String toString()
    {
        String result = "";

        for (int i = 0; i < this.path.size(); i++)
        {
            PathDirection direction = this.path.get(i);

            result += direction.toString();
        }

        return result;
    }
}
