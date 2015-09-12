package rznw.map.generator;

import rznw.map.Map;
import rznw.map.element.Wall;
import rznw.utility.RandomNumberGenerator;

import java.util.List;
import java.util.Vector;

public class MapTerrainGenerator
{
    private static int MIN_ROOM_SIZE = 5;
    private static int MAX_ROOM_SIZE = 15;

    private static int ROOM_BUFFER_SIZE = 3;

    private MapAreaCollision collision;

    public MapTerrainGenerator()
    {
        this.collision = new MapAreaCollision();
    }

    public List<MapArea> generateTerrain(Map map)
    {
        Vector<MapArea> rooms = new Vector<MapArea>();

        MapArea largestOpenArea = new MapArea(0, 0, Map.NUM_COLUMNS - 1, Map.NUM_ROWS - 1);
        int maxRoomSize = largestOpenArea.getSmallestDimensionSize();

        while (maxRoomSize >= MapTerrainGenerator.MIN_ROOM_SIZE)
        {
            MapArea room = this.generateRoom(map, largestOpenArea, maxRoomSize);
            rooms.add(room);

            largestOpenArea = this.calculateLargestOpenArea(map, rooms);
            maxRoomSize = largestOpenArea.getSmallestDimensionSize();
        }

        return rooms;
    }

    public static MapArea addBordersToOpenArea(MapArea openArea)
    {
        int startX = openArea.getStartX();
        startX = Math.max(0, startX - MapTerrainGenerator.ROOM_BUFFER_SIZE);

        int endX = openArea.getEndX();
        endX = Math.min(Map.NUM_COLUMNS - 1, endX + MapTerrainGenerator.ROOM_BUFFER_SIZE);

        int startY = openArea.getStartY();
        startY = Math.max(0, startY - MapTerrainGenerator.ROOM_BUFFER_SIZE);

        int endY = openArea.getEndY();
        endY = Math.min(Map.NUM_ROWS - 1, endY + MapTerrainGenerator.ROOM_BUFFER_SIZE);

        return new MapArea(startX, startY, endX, endY);
    }

    public static MapArea addBordersToOpenArea(MapArea openArea, int bufferSize)
    {
        int startX = openArea.getStartX();
        startX = Math.max(0, startX - bufferSize);

        int endX = openArea.getEndX();
        endX = Math.min(Map.NUM_COLUMNS - 1, endX + bufferSize);

        int startY = openArea.getStartY();
        startY = Math.max(0, startY - bufferSize);

        int endY = openArea.getEndY();
        endY = Math.min(Map.NUM_ROWS - 1, endY + bufferSize);

        return new MapArea(startX, startY, endX, endY);
    }

    public static void renderRoom(Map map, int startX, int startY, int endX, int endY)
    {
        for (int i = startX; i <= endX; i++)
        {
            map.setElement(startY, i, new Wall(startY, i));
            map.setElement(endY, i, new Wall(endY, i));
        }

        for (int i = startY; i <= endY; i++)
        {
            map.setElement(i, startX, new Wall(i, startX));
            map.setElement(i, endX, new Wall(i, endX));
        }
    }

    private MapArea generateRoom(Map map, MapArea largestOpenArea, int maxRoomSize)
    {
        maxRoomSize = Math.min(maxRoomSize, MapTerrainGenerator.MAX_ROOM_SIZE);
        int width = RandomNumberGenerator.randomInteger(MapTerrainGenerator.MIN_ROOM_SIZE, maxRoomSize);
        int height = RandomNumberGenerator.randomInteger(MapTerrainGenerator.MIN_ROOM_SIZE, maxRoomSize);

        int startX = RandomNumberGenerator.randomInteger(largestOpenArea.getStartX(), largestOpenArea.getMaxStartXForRectangle(width));
        int startY = RandomNumberGenerator.randomInteger(largestOpenArea.getStartY(), largestOpenArea.getMaxStartYForRectangle(height));
        int endX = startX + width - 1;
        int endY = startY + height - 1;

        this.renderRoom(map, startX, startY, endX, endY);

        return new MapArea(startX, startY, endX, endY);
    }

    private MapArea calculateLargestOpenArea(Map map, List<MapArea> rooms)
    {
        for (int width = Map.NUM_ROWS; width >= 0; width--)
        {
            for (int row = 0; row < Map.NUM_ROWS - width; row++) {
                for (int column = 0; column < Map.NUM_COLUMNS - width; column++) {

                    MapArea openArea = new MapArea(column, row, column + width - 1, row + width - 1);
                    MapArea openAreaWithBorders = this.addBordersToOpenArea(openArea);
                    if (!this.collision.elementExistsWithinRectangle(map, openAreaWithBorders) && !this.collision.areaFallsWithinAnotherArea(openArea, rooms))
                    {
                        return openArea;
                    }
                }
            }
        }

        return null;
    }
}
