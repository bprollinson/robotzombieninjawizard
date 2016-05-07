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
        System.out.println("Casting Steal Equipment");

        MainCharacter character = gameWorld.getMainCharacter();

        int row = character.getMapElement().getRow();
        int column = character.getMapElement().getColumn();

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(0, 0, direction);

        row += positionChange.getDeltaRow();
        column += positionChange.getDeltaColumn();

        Map map = gameWorld.getMap();
        MapElement element = map.getElement(row, column);

        if (element instanceof EnemyMapElement)
        {
            int stealProbability = 5 * spellPoints;

            if (RandomNumberGenerator.rollSucceeds(stealProbability))
            {
                System.out.println("Steal success");

                EnemyCharacter enemy = (EnemyCharacter)((EnemyMapElement)element).getCharacter();
                EquipmentItem equipmentItem = enemy.getEquipmentDrop();

                try
                {
                    character.getEquipment().addEquipment(new EquipmentGroup(equipmentItem, 1));
                    System.out.println("Stole equipment: " + equipmentItem.getDisplayName());
                }
                catch (EquipmentFullException efe)
                {
                    System.out.println("Equipment full");
                }
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
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Chance to steal: " + 5 * spellPoints + "%"
        };
    }
}
