package rznw.map.generator.area;

import rznw.map.Map;

public class OpenAreaPadder
{
    public MapArea addBordersToOpenArea(MapArea openArea, int bufferSize)
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
}
