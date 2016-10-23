package rznw.game.spell.ninja;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapElementSetter;
import rznw.map.MapRayTracer;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;

public class RoundhouseStrikeSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Roundhouse Strike";
    }

    public String getDescription()
    {
        return "You lunge in a straight line until an enemy is encountered. Then, you perform a roundhouse kick, damaging all enemies in spaces adjacent to you.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Roundhouse Strike");

        MainCharacter character = gameWorld.getMainCharacter();
        MapElement characterElement = character.getMapElement();

        Map map = gameWorld.getMap();
        map.setElement(characterElement.getRow(), characterElement.getColumn(), null);

        MapElement element = new MapRayTracer(map).findNextElementInDirection(characterElement, direction);

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(direction);
        int characterRow = element.getRow() - positionChange.getDeltaRow();
        int characterColumn = element.getColumn() - positionChange.getDeltaColumn();

        MapElementSetter.setElement(map, characterElement, characterRow, characterColumn);

        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - 1, characterElement.getColumn() - 1, characterElement.getRow() + 1, characterElement.getColumn() + 1);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            System.out.println("Before: " + enemy.getHP());
            int damage = 100 + 20 * spellPoints;
            enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("After: " + enemy.getHP());
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
            "Damage: " + (100 + 20 * spellPoints),
            "Radius: 1"
        };
    }
}
