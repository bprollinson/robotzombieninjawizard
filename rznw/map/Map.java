package rznw.map;

import java.util.Collection;
import java.util.Vector;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;

public class Map
{
    public static final int NUM_ROWS = 30;
    public static final int NUM_COLUMNS = 40;

    private MapElement[][] elements;
    private MapElement[][] backgroundElements;

    public Map()
    {
        this.elements = new MapElement[Map.NUM_ROWS][Map.NUM_COLUMNS];
        this.backgroundElements = new MapElement[Map.NUM_ROWS][Map.NUM_COLUMNS];
    }

    public MapElement getElement(int i, int j)
    {
        return this.elements[i][j];
    }

    public MapElement getBackgroundElement(int i, int j)
    {
        return this.backgroundElements[i][j];
    }

    public void setElement(int i, int j, MapElement element)
    {
        this.elements[i][j] = element;
    }

    public void setBackgroundElement(int i, int j, MapElement element)
    {
        this.backgroundElements[i][j] = element;
    }

    public Collection<EnemyCharacter> getEnemies()
    {
        Collection<EnemyCharacter> enemies = new Vector<EnemyCharacter>();

        for (int i = 0; i < Map.NUM_ROWS; i++)
        {
            for (int j = 0; j < Map.NUM_COLUMNS; j++)
            {
                MapElement element = this.elements[i][j];

                if (element instanceof EnemyMapElement)
                {
                    EnemyCharacter enemyCharacter = (EnemyCharacter)((EnemyMapElement)element).getCharacter();
                    enemies.add(enemyCharacter);
                }
            }
        }

        return enemies;
    }
}
