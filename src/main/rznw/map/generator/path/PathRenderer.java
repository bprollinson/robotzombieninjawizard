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
            map.setElement(point.getRow(), point.getColumn(), null);
            map.setBackgroundElement(point.getRow(), point.getColumn(), new Path(point.getRow(), point.getColumn()));
        }

        MapPoint point1 = points[0];
        PathDirection direction1 = path.getDirection(0);
        direction1 = direction1.getOppositeDirection();
        int row1 = point1.getRow() + direction1.getDeltaRow();
        int column1 = point1.getColumn() + direction1.getDeltaColumn();
        map.setElement(row1, column1, null);

        MapPoint point2 = points[points.length - 1];
        PathDirection direction2 = path.getDirection(points.length - 2);
        int row2 = point2.getRow() + direction2.getDeltaRow();
        int column2 = point2.getColumn() + direction2.getDeltaColumn();
        map.setElement(row2, column2, null);
    }
}
