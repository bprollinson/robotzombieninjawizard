package rznw.save;

import rznw.map.GameWorld;
import rznw.map.Map;

import java.io.BufferedWriter;

public class MapVisibilitySaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        Map map = gameWorld.getMap();

        this.writeLine(fileWriter, this.getNumVisibleElements(map));

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                if (map.isVisible(row, column))
                {
                    this.writeLine(fileWriter, row);
                    this.writeLine(fileWriter, column);
                }
            }
        }
    }

    private int getNumVisibleElements(Map map)
    {
        int numVisibleElements = 0;

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                if (map.isVisible(row, column))
                {
                    numVisibleElements++;
                }
            }
        }

        return numVisibleElements;
    }
}
