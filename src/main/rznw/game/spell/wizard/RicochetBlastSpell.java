package rznw.game.spell.wizard;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapElementSetter;
import rznw.map.MapRayTracer;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.ui.LogRendererFactory;

public class RicochetBlastSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Ricochet Blast";
    }

    public String getDescription()
    {
        return "Shoots a projectile in the chosen direction. An enemy hit by this projectile will be shot back, hitting and damaging other enemies in a chain reaction.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        LogRendererFactory.instance().log("Casting ricochet blast.");

        MainCharacter character = gameWorld.getMainCharacter();

        Map map = gameWorld.getMap();
        MapElement element = new MapRayTracer(map).findNextElementInDirection(character.getMapElement(), direction);
        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(direction);

        if (element.isEnemy())
        {
            int damage = 10 * spellPoints;

            while (element != null)
            {
                element = this.ricochetEnemy(element, damage, character, gameWorld, positionChange.getDeltaRow(), positionChange.getDeltaColumn(), map);
            }
        }
    }

    private MapElement ricochetEnemy(MapElement element, int damage, MainCharacter character, GameWorld gameWorld, int deltaRow, int deltaColumn, Map map)
    {
        damage = ((EnemyMapElement)element).getCharacter().damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
        LogRendererFactory.instance().log("Dealt " + damage + " damage to " + ((EnemyMapElement)element).getCharacter().getLogName() + ".");

        while (true)
        {
            int newRow = element.getRow() + deltaRow;
            int newColumn = element.getColumn() + deltaColumn;

            if (map.getElement(newRow, newColumn) != null && map.getElement(newRow, newColumn).isEnemy())
            {
                return map.getElement(newRow, newColumn);
            }

            if (map.getElement(newRow, newColumn) != null)
            {
                return null;
            }

            map.setElement(element.getRow(), element.getColumn(), null);
            MapElementSetter.setElement(map, element, newRow, newColumn);
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
            "Damage: " + 10 * spellPoints
        };
    }
}
