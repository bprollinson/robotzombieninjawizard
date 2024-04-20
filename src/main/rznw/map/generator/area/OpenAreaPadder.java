package rznw.map.generator.area;

import rznw.map.Map;

public class OpenAreaPadder
{
    public MapArea addBordersToOpenArea(MapArea openArea, int bufferSize)
    {
        int startColumn = openArea.getStartColumn();
        startColumn = Math.max(0, startColumn - bufferSize);

        int endColumn = openArea.getEndColumn();
        endColumn = Math.min(Map.NUM_COLUMNS - 1, endColumn + bufferSize);

        int startRow = openArea.getStartRow();
        startRow = Math.max(0, startRow - bufferSize);

        int endRow = openArea.getEndRow();
        endRow = Math.min(Map.NUM_ROWS - 1, endRow + bufferSize);

        return new MapArea(startRow, startColumn, endRow, endColumn);
    }
}
