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
        System.out.println("Casting Heat Ray");

        MainCharacter character = gameWorld.getMainCharacter();

        int damage = 50 + 10 * spellPoints;

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(0, 0, direction);

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

            if (element instanceof Wall)
            {
                wallFound = true;
            }

            if (element instanceof EnemyMapElement)
            {
                Character enemy = ((EnemyMapElement)element).getCharacter();
                System.out.println("Before: " + enemy.getHP());
                enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
                System.out.println("After: " + enemy.getHP());
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
