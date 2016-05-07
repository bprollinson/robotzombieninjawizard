package rznw.game.spell.robot;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;

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
        System.out.println("Casting Electric Field");

        Map map = gameWorld.getMap();

        int radius = 1 + (int)Math.floor(spellPoints / 4);
        System.out.println("Radius is: " + radius);

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(0, 0, direction);

        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();

        System.out.println("Character position is: " + characterElement.getRow() + ", " + characterElement.getColumn());

        int row = characterElement.getRow() + positionChange.getDeltaRow() * (radius + 1);
        int column = characterElement.getColumn() + positionChange.getDeltaColumn() * (radius + 1);

        System.out.println("Center row and column are: " + row + ", " + column);

        int damage = 100 + 20 * spellPoints;

        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            System.out.println("Damaging an enemy");

            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            System.out.println("Enemy HP before: " + enemy.getHP());
            enemy.damage(damage, gameWorld.getMainCharacter(), gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("Enemy HP after: " + enemy.getHP());
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
