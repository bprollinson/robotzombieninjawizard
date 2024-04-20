package rznw.game.spell.ninja;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class StealGoldSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Steal Gold";
    }

    public String getDescription()
    {
        return "Has a chance to steal gold from an ememy within melee combat range.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        LogRendererFactory.instance().log("Casting steal gold.");

        MainCharacter character = gameWorld.getMainCharacter();

        int row = character.getMapElement().getRow();
        int column = character.getMapElement().getColumn();

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(direction);

        row += positionChange.getDeltaRow();
        column += positionChange.getDeltaColumn();

        Map map = gameWorld.getMap();
        MapElement element = map.getElement(row, column);

        if (element != null && element.isEnemy())
        {
            int stealProbability = 5 * spellPoints;

            if (RandomNumberGenerator.rollSucceeds(stealProbability))
            {
                EnemyCharacter enemy = (EnemyCharacter)((EnemyMapElement)element).getCharacter();
                int numGoldPercent = 20 + 10 * spellPoints;
                int baseGold = enemy.getNumGold();

                int numGold = (int)Math.floor(numGoldPercent / 100.0 * baseGold);
                character.getInventory().addGold(numGold);

                LogRendererFactory.instance().log("Stole " + numGold + " gold from enemy.");
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int numGoldPercent = 20 + 10 * spellPoints;

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Chance to steal: " + 5 * spellPoints + "%",
            "Gold stolen: " + numGoldPercent + "% of base enemy gold"
        };
    }
}
