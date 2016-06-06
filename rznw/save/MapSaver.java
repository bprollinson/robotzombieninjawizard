package rznw.save;

import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

import java.io.BufferedWriter;

public class MapSaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        Map map = gameWorld.getMap();

        this.writeLine(fileWriter, map.getLevel());

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);

                if (element != null)
                {
                    this.writeLine(fileWriter, row + "," + column);
                    this.writeLine(fileWriter, element.getClass().getName());
                }
            }
        }
    }
}
