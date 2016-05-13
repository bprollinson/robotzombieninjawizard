package rznw.map.generator.area;

import rznw.map.generator.MapPoint;
import rznw.map.generator.direction.PathDirection;

public class MapAreaPointAggregator
{
    public MapPoint[] getWallPoints(MapArea area, PathDirection directionOut)
    {
        if (directionOut.getDeltaRow() < 0)
        {
            return this.getHorizontalWallPoints(area, area.getStartRow());
        }

        if (directionOut.getDeltaRow() > 0)
        {
            return this.getHorizontalWallPoints(area, area.getEndRow());
        }

        if (directionOut.getDeltaColumn() < 0)
        {
            return this.getVerticalWallPoints(area, area.getStartColumn());
        }

        return this.getVerticalWallPoints(area, area.getEndColumn());
    }

    private MapPoint[] getHorizontalWallPoints(MapArea area, int pointY)
    {
        int numPoints = area.getEndColumn() - area.getStartColumn() - 1;
        MapPoint[] points = new MapPoint[numPoints];

        for (int i = 0; i < numPoints; i++)
        {
            int pointX = area.getStartColumn() + i + 1;
            points[i] = new MapPoint(pointY, pointX);
        }

        return points;
    }

    private MapPoint[] getVerticalWallPoints(MapArea area, int pointX)
    {
        int numPoints = area.getEndRow() - area.getStartRow() - 1;
        MapPoint[] points = new MapPoint[numPoints];

        for (int i = 0; i < numPoints; i++)
        {
            int pointY = area.getStartRow() + i + 1;
            points[i] = new MapPoint(pointY, pointX);
        }

        return points;
    }
}
