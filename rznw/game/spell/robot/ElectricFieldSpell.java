package rznw.game.spell.robot;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.ui.LogRendererFactory;

import java.util.Collection;
import java.util.Iterator;

public class ElectricFieldSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Electric Field";
    }

    public String getDescription()
    {
        return "Causes widespread damage over an area on the map in any of the four directions from you.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        LogRendererFactory.instance().log("Casting electric field.");

        Map map = gameWorld.getMap();

        int radius = 1 + (int)Math.floor(spellPoints / 4);

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(direction);

        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();

        int row = characterElement.getRow() + positionChange.getDeltaRow() * (radius + 1);
        int column = characterElement.getColumn() + positionChange.getDeltaColumn() * (radius + 1);

        int damage = 100 + 20 * spellPoints;

        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            damage = enemy.damage(damage, gameWorld.getMainCharacter(), gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            LogRendererFactory.instance().log("Dealt " + damage + " damage to " + enemy.getLogName() + ".");
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int radius = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + (100 + 20 * spellPoints),
            "Radius: " + radius
        };
    }
}
