package rznw.map.generator.area;

import rznw.map.generator.MapPoint;
import rznw.map.generator.direction.PathDirection;

public class MapAreaPointAggregator
{
    public MapPoint[] getWallPoints(MapArea area, PathDirection directionOut)
    {
        if (directionOut.getDeltaRow() < 0)
        {
            return this.getHorizontalWallPoints(area, area.getStartY());
        }

        if (directionOut.getDeltaRow() > 0)
        {
            return this.getHorizontalWallPoints(area, area.getEndY());
        }

        if (directionOut.getDeltaColumn() < 0)
        {
            return this.getVerticalWallPoints(area, area.getStartX());
        }

        return this.getVerticalWallPoints(area, area.getEndX());
    }

    private MapPoint[] getHorizontalWallPoints(MapArea area, int pointY)
    {
        int numPoints = area.getEndX() - area.getStartX() - 1;
        MapPoint[] points = new MapPoint[numPoints];

        for (int i = 0; i < numPoints; i++)
        {
            int pointX = area.getStartX() + i + 1;
            points[i] = new MapPoint(pointX, pointY);
        }

        return points;
    }

    private MapPoint[] getVerticalWallPoints(MapArea area, int pointX)
    {
        int numPoints = area.getEndY() - area.getStartY() - 1;
        MapPoint[] points = new MapPoint[numPoints];

        for (int i = 0; i < numPoints; i++)
        {
            int pointY = area.getStartY() + i + 1;
            points[i] = new MapPoint(pointX, pointY);
        }

        return points;
    }
}
