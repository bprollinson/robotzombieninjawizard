package rznw.map;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.element.Stairs;
import rznw.game.skill.Skill;

public class MapElementVisitor
{
    private Map map;

    public MapElementVisitor(Map map)
    {
        this.map = map;
    }

    public void visit(MainCharacter character, int row, int column)
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
                this.map.setVisible(character, rangeRow, rangeColumn);
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
                if (this.map.getBackgroundElement(rangeRow, rangeColumn) instanceof Stairs)
                {
                    if (!this.map.isVisible(rangeRow, rangeColumn))
                    {
                        System.out.println("Found stairs using skill");
                    }
                    this.map.setVisible(character, rangeRow, rangeColumn);
                }
            }
        }
    }
}
