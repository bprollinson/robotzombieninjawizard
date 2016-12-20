package rznw.map;

import rznw.game.SummonedCharacter;
import rznw.game.enemy.EnemyCharacter;
import rznw.map.element.MapElement;
import rznw.game.maincharacter.MainCharacter;

import java.util.Collection;

public class Map
{
    public static final int NUM_ROWS = 30;
    public static final int NUM_COLUMNS = 40;
    public static final int MAX_LEVEL = 12;

    private int level;
    private MapElement[][] elements;
    private MapElement[][] backgroundElements;
    private boolean[][] visible;
    private boolean[][] visited;

    public Map(int level)
    {
        this.level = level;

        this.elements = new MapElement[Map.NUM_ROWS][Map.NUM_COLUMNS];
        this.backgroundElements = new MapElement[Map.NUM_ROWS][Map.NUM_COLUMNS];
        this.visible = new boolean[Map.NUM_ROWS][Map.NUM_COLUMNS];
        this.visited = new boolean[Map.NUM_ROWS][Map.NUM_COLUMNS];

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                this.visible[row][column] = false;
                this.visited[row][column] = false;
            }
        }
    }

    public int getLevel()
    {
        return this.level;
    }

    public MapElement getElement(int row, int column)
    {
        return this.elements[row][column];
    }

    public MapElement getBackgroundElement(int row, int column)
    {
        return this.backgroundElements[row][column];
    }

    public void setElement(int row, int column, MapElement element)
    {
        this.elements[row][column] = element;
    }

    public void setBackgroundElement(int row, int column, MapElement element)
    {
        this.backgroundElements[row][column] = element;
    }

    public void setVisible(MainCharacter character, int row, int column, boolean reveal)
    {
        if (!reveal)
        {
            this.visible[row][column] = true;
            return;
        }

        this.setVisible(character, row, column);
    }

    public void setVisible(MainCharacter character, int row, int column)
    {
        if (this.visible[row][column] == false)
        {
            MapElement backgroundElement = this.getBackgroundElement(row, column);
            if (backgroundElement != null)
            {
                backgroundElement.reveal(character);
            }
        }

        this.visible[row][column] = true;
    }

    public boolean isVisible(int row, int column)
    {
        return this.visible[row][column];
    }

    public void setElementVisited(MainCharacter character, int row, int column)
    {
        new MapElementVisitor(this).visit(character, row, column);
    }

    public Collection<EnemyCharacter> getEnemies()
    {
        return new MapCharacterScraper().getEnemies(this);
    }

    public Collection<SummonedCharacter> getSummons()
    {
        return new MapCharacterScraper().getSummons(this);
    }

    public Collection<EnemyCharacter> getEnemiesInRectangle(int minRow, int minColumn, int maxRow, int maxColumn)
    {
        return new MapCharacterScraper().getEnemiesInRectangle(this, minRow, minColumn, maxRow, maxColumn);
    }

    public boolean elementVisited(int row, int column)
    {
        return this.visited[row][column];
    }

    public void visit(int row, int column)
    {
        this.visited[row][column] = true;
    }

    public boolean isLastLevel()
    {
        return this.level == Map.MAX_LEVEL;
    }
}
