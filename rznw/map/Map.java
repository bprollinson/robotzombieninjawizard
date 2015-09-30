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

    private static final int FOG_RADIUS = 2;

    private int level;
    private MapElement[][] elements;
    private MapElement[][] backgroundElements;
    private boolean[][] visible;

    public Map(int level)
    {
        this.level = level;

        this.elements = new MapElement[Map.NUM_ROWS][Map.NUM_COLUMNS];
        this.backgroundElements = new MapElement[Map.NUM_ROWS][Map.NUM_COLUMNS];
        this.visible = new boolean[Map.NUM_ROWS][Map.NUM_COLUMNS];

        for (int i = 0; i < Map.NUM_ROWS; i++)
        {
            for (int j = 0; j < Map.NUM_COLUMNS; j++)
            {
                this.visible[i][j] = false;
            }
        }
    }

    public int getLevel()
    {
        return this.level;
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

    public boolean isVisible(int i, int j)
    {
        return this.visible[i][j];
    }

    public void setElementVisited(int i, int j)
    {
        int minRow = Math.max(i - Map.FOG_RADIUS, 0);
        int maxRow = Math.min(i + Map.FOG_RADIUS, Map.NUM_ROWS - 1);
        int minColumn = Math.max(j - Map.FOG_RADIUS, 0);
        int maxColumn = Math.min(j + Map.FOG_RADIUS, Map.NUM_COLUMNS - 1);

        for (int row = minRow; row <= maxRow; row++)
        {
            for (int column = minColumn; column <= maxColumn; column++)
            {
                this.visible[row][column] = true;
            }
        }
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
