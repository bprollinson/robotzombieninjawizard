package rznw.game.spell.ninja;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public class StealExperienceSpell extends Spell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Steal Experience");

        MainCharacter character = gameWorld.getMainCharacter();

        int row = character.getMapElement().getRow();
        int column = character.getMapElement().getColumn();

        int deltaRow = 0;
        int deltaColumn = 0;

        switch(direction)
        {
            case Spell.DIRECTION_UP:
                deltaRow = -1;
                break;
            case Spell.DIRECTION_DOWN:
                deltaRow = 1;
                break;
            case Spell.DIRECTION_LEFT:
                deltaColumn = -1;
                break;
            case Spell.DIRECTION_RIGHT:
                deltaColumn = 1;
                break;
        }

        row += deltaRow;
        column += deltaColumn;

        Map map = gameWorld.getMap();
        MapElement element = map.getElement(row, column);

        if (element instanceof EnemyMapElement)
        {
            int stealProbability = 5 * spellPoints;

            if (RandomNumberGenerator.rollSucceeds(stealProbability))
            {
                System.out.println("Steal success");

                EnemyCharacter enemy = (EnemyCharacter)((EnemyMapElement)element).getCharacter();
                int numExperiencePercent = 20 + 10 * spellPoints;
                int baseExperience = enemy.getExperienceReward();

                int numExperience = (int)Math.floor(numExperiencePercent / 100.0 * baseExperience);
                character.grantExperience(numExperience);

                System.out.println("Steal percentage: " + numExperiencePercent);
                System.out.println("Base experience: " + baseExperience);
                System.out.println("Experience stolen: " + numExperience);
            }
            else
            {
                System.out.println("Steal failure - bad roll");
            }
        }
        else
        {
            System.out.println("Steal failure - not an enemy");
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public boolean requiresDirectionInput()
    {
        return true;
    }
}
