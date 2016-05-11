package rznw.map.generator.path;

import rznw.map.Map;
import rznw.map.element.Path;
import rznw.map.generator.MapPoint;
import rznw.map.generator.direction.PathDirection;

public class PathRenderer
{
    public void renderPathOnMap(Map map, MapPath path)
    {
        MapPoint[] points = path.getPoints();

        for (int i = 0; i < points.length; i++)
        {
            MapPoint point = points[i];
            map.setElement(point.getY(), point.getX(), null);
            map.setBackgroundElement(point.getY(), point.getX(), new Path(point.getY(), point.getX()));
        }

        MapPoint point1 = points[0];
        PathDirection direction1 = path.getDirection(0);
        direction1 = direction1.getOppositeDirection();
        int x1 = point1.getX() + direction1.getDeltaColumn();
        int y1 = point1.getY() + direction1.getDeltaRow();
        map.setElement(y1, x1, null);

        MapPoint point2 = points[points.length - 1];
        PathDirection direction2 = path.getDirection(points.length - 2);
        int x2 = point2.getX() + direction2.getDeltaColumn();
        int y2 = point2.getY() + direction2.getDeltaRow();
        map.setElement(y2, x2, null);
    }
}
