package rznw.game.spell.ninja;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.InventoryFullException;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class StealItemSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Steal Item";
    }

    public String getDescription()
    {
        return "Has a chance to steal an item from an enemy within melee combat range.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        LogRendererFactory.instance().log("Casting steal item.");

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
                InventoryItemGroup itemGroup = new InventoryItemGroup(enemy.getItemDrop(), 1);
                try
                {
                    character.getInventory().addItems(itemGroup);
                    LogRendererFactory.instance().log("Stole " + itemGroup.getItem().getDisplayName().toLowerCase() + " from enemy.");
                }
                catch (InventoryFullException ife)
                {
                    LogRendererFactory.instance().log("Can't steal item - inventory full.");
                }
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Chance to steal: " + 5 * spellPoints + "%"
        };
    }
}
