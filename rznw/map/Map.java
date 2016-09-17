package rznw.map;

import java.util.Collection;
import java.util.Vector;

import rznw.game.SummonedCharacter;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.skill.Skill;
import rznw.map.element.MapElement;
import rznw.map.element.Stairs;
import rznw.map.element.SummonedMinionMapElement;
import rznw.game.maincharacter.MainCharacter;

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
        int radius = character.getViewRadius();

        int minRow = Math.max(row - radius, 0);
        int maxRow = Math.min(row + radius, Map.NUM_ROWS - 1);
        int minColumn = Math.max(column - radius, 0);
        int maxColumn = Math.min(column + radius, Map.NUM_COLUMNS - 1);

        for (int rangeRow = minRow; rangeRow <= maxRow; rangeRow++)
        {
            for (int rangeColumn = minColumn; rangeColumn <= maxColumn; rangeColumn++)
            {
                this.setVisible(character, rangeRow, rangeColumn);
            }
        }

        radius = 3 * character.getSkillPoints(Skill.SKILL_FIND_STAIRS);
        System.out.println("Looking for stairs with radius: " + radius);
        minRow = Math.max(row - radius, 0);
        maxRow = Math.min(row + radius, Map.NUM_ROWS - 1);
        minColumn = Math.max(column - radius, 0);
        maxColumn = Math.min(column + radius, Map.NUM_COLUMNS - 1);

        for (int rangeRow = minRow; rangeRow <= maxRow; rangeRow++)
        {
            for (int rangeColumn = minColumn; rangeColumn <= maxColumn; rangeColumn++)
            {
                if (this.getBackgroundElement(rangeRow, rangeColumn) instanceof Stairs)
                {
                    if (!this.isVisible(rangeRow, rangeColumn))
                    {
                        System.out.println("Found stairs using skill");
                    }
                    this.setVisible(character, rangeRow, rangeColumn);
                }
            }
        }
    }

    public Collection<EnemyCharacter> getEnemies()
    {
        return new MapScraper().getEnemies(this);
    }

    public Collection<SummonedCharacter> getSummons()
    {
        return new MapScraper().getSummons(this);
    }

    public Collection<EnemyCharacter> getEnemiesInRectangle(int minRow, int minColumn, int maxRow, int maxColumn)
    {
        return new MapScraper().getEnemiesInRectangle(this, minRow, minColumn, maxRow, maxColumn);
    }

    public boolean elementVisited(int row, int column)
    {
        return this.visited[row][column];
    }

    public void visit(int row, int column)
    {
        System.out.println("Setting a map square as visited");
        this.visited[row][column] = true;
    }

    public boolean isLastLevel()
    {
        return this.level == Map.MAX_LEVEL;
    }
}
