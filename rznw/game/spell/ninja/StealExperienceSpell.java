package rznw.game.spell.ninja;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.utility.RandomNumberGenerator;

public class StealExperienceSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Steal Experience";
    }

    public String getDescription()
    {
        return "Has a chance to steal experience from an enemy within melee combat range.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Steal Experience");

        MainCharacter character = gameWorld.getMainCharacter();

        int row = character.getMapElement().getRow();
        int column = character.getMapElement().getColumn();

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(0, 0, direction);

        row += positionChange.getDeltaRow();
        column += positionChange.getDeltaColumn();

        Map map = gameWorld.getMap();
        MapElement element = map.getElement(row, column);

        if (element != null && element.isEnemy())
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

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int numExperiencePercent = 20 + 10 * spellPoints;

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Chance to steal: " + 5 * spellPoints + "%",
            "Experience stolen: " + numExperiencePercent + "% of base enemy exp"
        };
    }
}
