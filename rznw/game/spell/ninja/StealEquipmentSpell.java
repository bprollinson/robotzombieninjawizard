package rznw.game.spell.ninja;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.spell.DirectedSpell;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class StealEquipmentSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Steal Equipment";
    }

    public String getDescription()
    {
        return "Has a chance to steal a piece of equipment from an enemy within melee combat range.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        LogRendererFactory.instance().log("Casting steal equipment.");

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
                EquipmentItem equipmentItem = enemy.getEquipmentDrop();

                try
                {
                    character.getEquipment().addEquipment(new EquipmentGroup(equipmentItem, 1));
                    LogRendererFactory.instance().log("Stole " + equipmentItem.getDisplayName().toLowerCase() + " from enemy.");
                }
                catch (EquipmentFullException efe)
                {
                    LogRendererFactory.instance().log("Your equipment storage is full!");
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
