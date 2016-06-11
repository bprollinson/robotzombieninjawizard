package rznw.save;

import rznw.game.Character;
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
                    Character enemyCharacter = ((EnemyMapElement)element).getCharacter();
                    this.writeEnemyInfo(fileWriter, enemyCharacter);
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

    private void writeEnemyInfo(BufferedWriter fileWriter, Character enemyCharacter)
    {
        this.writeLine(fileWriter, enemyCharacter.getClass().getName());
        this.writeLine(fileWriter, enemyCharacter.getHP());
        this.writeLine(fileWriter, enemyCharacter.getMP());
    }
}