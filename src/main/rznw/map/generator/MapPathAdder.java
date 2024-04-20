package rznw.map.generator;

import rznw.map.Map;
import rznw.map.ShortestPathCalculator;
import rznw.map.generator.area.MapArea;
import rznw.map.generator.area.MapAreaPointAggregator;
import rznw.map.generator.area.OpenAreaPadder;
import rznw.map.generator.area.RoomRenderer;
import rznw.map.generator.direction.PathDirection;
import rznw.map.generator.direction.PathDirectionFactory;
import rznw.map.generator.path.MapPath;
import rznw.map.generator.path.PathCollection;
import rznw.map.generator.path.PathRenderer;
import rznw.utility.RandomNumberGenerator;

import java.util.List;

public class MapPathAdder
{
    private MapAreaPointAggregator pointAggregator;
    private OpenAreaPadder padder;
    private RoomRenderer roomRenderer;
    private PathRenderer pathRenderer;

    public MapPathAdder()
    {
        this.pointAggregator = new MapAreaPointAggregator();
        this.padder = new OpenAreaPadder();
        this.roomRenderer = new RoomRenderer();
        this.pathRenderer = new PathRenderer();
    }

    public void addPath(Map map, List<MapArea> rooms, PathCollection pathCollection, int i, int j)
    {
        MapArea room1 = rooms.get(i);
        MapArea room2 = rooms.get(j);

        pathCollection.setDirectlyAdjacent(i, j);

        PathDirection[] possibleDirectionOut = PathDirectionFactory.getFromRooms(room1, room2);

        int outIndex = RandomNumberGenerator.randomInteger(0, possibleDirectionOut.length - 1);
        PathDirection directionOut = possibleDirectionOut[outIndex];

        MapPoint point1 = this.getRandomWallAdjacentPoint(room1, directionOut);

        PathDirection[] possibleDirectionIn = PathDirectionFactory.getOppositeDirections(possibleDirectionOut);

        int inIndex = RandomNumberGenerator.randomInteger(0, possibleDirectionIn.length - 1);
        PathDirection directionIn = possibleDirectionIn[inIndex];

        MapPoint point2 = this.getRandomWallAdjacentPoint(room2, directionIn);

        MapPath path = this.calculatePath(map, rooms, point1, point2);

        this.pathRenderer.renderPathOnMap(map, path);
    }

    private MapPoint getRandomWallAdjacentPoint(MapArea room, PathDirection directionFromRoom)
    {
        MapPoint[] wallPoints = this.pointAggregator.getWallPoints(room, directionFromRoom);

        MapPoint[] possiblePoints = new MapPoint[wallPoints.length];

        for (int i = 0; i < wallPoints.length; i++)
        {
            MapPoint wallPoint = wallPoints[i];
            possiblePoints[i] = new MapPoint(wallPoint.getRow() + directionFromRoom.getDeltaRow(), wallPoint.getColumn() + directionFromRoom.getDeltaColumn());
        }

        int randomIndex = RandomNumberGenerator.randomInteger(0, possiblePoints.length - 1);
        return possiblePoints[randomIndex];
    }

    private MapPath calculatePath(Map map, List<MapArea> rooms, MapPoint point1, MapPoint point2)
    {
        Map paddedMap = new Map(map.getLevel());

        for (int i = 0; i < rooms.size(); i++)
        {
            MapArea room = rooms.get(i);
            MapArea paddedRoom = this.padder.addBordersToOpenArea(room, 1);

            this.roomRenderer.renderRoom(paddedMap, paddedRoom.getStartRow(), paddedRoom.getStartColumn(), paddedRoom.getEndRow(), paddedRoom.getEndColumn());
        }

        ShortestPathCalculator pathCalculator = new ShortestPathCalculator(paddedMap, true, false);

        return pathCalculator.calculateShortestPath(point1, point2, false);
    }
}
