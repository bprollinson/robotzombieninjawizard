package rznw.game.spell.ninja;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.game.statuseffects.StatusEffectStats;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapElementSetter;
import rznw.map.MapRayTracer;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;

public class ArmorBreakSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Armor Break";
    }

    public String getDescription()
    {
        return "You lunge in a straight line until an enemy is encountered. You damage that enemy and decrease its defensive capability.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Armor Break");

        MainCharacter character = gameWorld.getMainCharacter();
        MapElement characterElement = character.getMapElement();

        Map map = gameWorld.getMap();
        map.setElement(characterElement.getRow(), characterElement.getColumn(), null);

        MapElement element = new MapRayTracer(map).findNextElementInDirection(characterElement, direction);

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(direction);
        int characterRow = element.getRow() - positionChange.getDeltaRow();
        int characterColumn = element.getColumn() - positionChange.getDeltaColumn();

        MapElementSetter.setElement(map, characterElement, characterRow, characterColumn);

        if (element.isEnemy())
        {
            System.out.println("Hitting enemy");
            int damage = 20 + 10 * spellPoints;

            Character enemy = ((EnemyMapElement)element).getCharacter();
            System.out.println("Enemy HP before: " + enemy.getHP());
            enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("Enemy HP after: " + enemy.getHP());

            int armorBreakPercent = 5 * spellPoints;
            enemy.getStatusEffects().setStat(StatusEffectStats.STAT_ARMOR_BREAK, armorBreakPercent);
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
            "Damage: " + (20 + 10 * spellPoints),
            "Armor reduction: " + 5 * spellPoints + "%"
        };
    }
}
