package rznw.save;

import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;

import java.io.BufferedWriter;

public class EnemySaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        Map map = gameWorld.getMap();

        this.writeLine(fileWriter, this.getNumEnemies(map));

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);

                if (element != null && element.isEnemy())
                {
                    this.writeLine(fileWriter, ((EnemyMapElement)element).getCharacter().getClass().getName());
                }
            }
        }
    }

    private int getNumEnemies(Map map)
    {
        int numEnemies = 0;

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);

                if (element != null && element.isEnemy())
                {
                    numEnemies++;
                }
            }
        }

        return numEnemies;
    }
}
