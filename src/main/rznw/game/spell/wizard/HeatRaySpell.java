package rznw.game.spell.wizard;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.map.element.Wall;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.ui.LogRendererFactory;

public class HeatRaySpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Heat Ray";
    }

    public String getDescription()
    {
        return "Travels through enemies, damaging them all until a wall is encountered.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        LogRendererFactory.instance().log("Casting heat ray.");

        MainCharacter character = gameWorld.getMainCharacter();

        int damage = 50 + 10 * spellPoints;

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(direction);

        boolean wallFound = false;
        int row = character.getMapElement().getRow();
        int column = character.getMapElement().getColumn();

        while (!wallFound)
        {
            row += positionChange.getDeltaRow();
            column += positionChange.getDeltaColumn();

            if (row < 0 || row >= Map.NUM_ROWS || column < 0 || column >= Map.NUM_COLUMNS)
            {
                break;
            }

            Map map = gameWorld.getMap();
            MapElement element = map.getElement(row, column);

            if (element == null)
            {
                continue;
            }

            if (element instanceof Wall)
            {
                wallFound = true;
            }

            if (element.isEnemy())
            {
                Character enemy = ((EnemyMapElement)element).getCharacter();
                int damageDealt = enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
                LogRendererFactory.instance().log("Dealt " + damageDealt + " damage to " + enemy.getLogName() + ".");
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int damage = 50 + 10 * spellPoints;

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + damage
        };
    }
}
