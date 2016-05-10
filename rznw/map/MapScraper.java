package rznw.map;

import rznw.game.SummonedCharacter;
import rznw.game.enemy.EnemyCharacter;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.map.element.SummonedMinionMapElement;

import java.util.Collection;
import java.util.Vector;

public class MapScraper
{
    public Collection<EnemyCharacter> getEnemies(Map map)
    {
        Collection<EnemyCharacter> enemies = new Vector<EnemyCharacter>();

        for (int i = 0; i < Map.NUM_ROWS; i++)
        {
            for (int j = 0; j < Map.NUM_COLUMNS; j++)
            {
                MapElement element = map.getElement(i, j);

                if (element != null && element.isEnemy())
                {
                    EnemyCharacter enemyCharacter = (EnemyCharacter)((EnemyMapElement)element).getCharacter();
                    enemies.add(enemyCharacter);
                }
            }
        }

        return enemies;
    }

    public Collection<SummonedCharacter> getSummons(Map map)
    {
        Collection<SummonedCharacter> summons = new Vector<SummonedCharacter>();

        for (int i = 0; i < Map.NUM_ROWS; i++)
        {
            for (int j = 0; j < Map.NUM_COLUMNS; j++)
            {
                MapElement element = map.getElement(i, j);

                if (element instanceof SummonedMinionMapElement)
                {
                    SummonedCharacter summonedCharacter = (SummonedCharacter)((SummonedMinionMapElement)element).getCharacter();
                    summons.add(summonedCharacter);
                }
            }
        }

        return summons;
    }

    public Collection<EnemyCharacter> getEnemiesInRectangle(Map map, int minRow, int minColumn, int maxRow, int maxColumn)
    {
        Collection<EnemyCharacter> enemies = new Vector<EnemyCharacter>();

        for (int i = 0; i < Map.NUM_ROWS; i++)
        {
            for (int j = 0; j < Map.NUM_COLUMNS; j++)
            {
                MapElement element = map.getElement(i, j);

                if (element != null && element.isEnemy() && i >= minRow && i <= maxRow && j >= minColumn && j <= maxColumn)
                {
                    EnemyCharacter enemyCharacter = (EnemyCharacter)((EnemyMapElement)element).getCharacter();
                    enemies.add(enemyCharacter);
                }
            }
        }

        return enemies;
    }

    public boolean mapContainsElementOfType(Map map, Class clazz)
    {
        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);

                if (element != null && clazz.isInstance(element))
                {
                    return true;
                }
            }
        }

        return false;
    }
}
