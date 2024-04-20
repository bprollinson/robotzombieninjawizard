package rznw.game.spell.ninja;

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
import rznw.utility.RandomNumberGenerator;

public class StunStrikeSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Stun Strike";
    }

    public String getDescription()
    {
        return "You lunge in a straight line until an enemy is encountered. You damage that enemy, with a chance to stun it.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        LogRendererFactory.instance().log("Casting stun strike.");

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
            int damage = 20 + 10 * spellPoints;
            int stunProbability = 5 * spellPoints;

            Character enemy = ((EnemyMapElement)element).getCharacter();
            damage = enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            LogRendererFactory.instance().log("Dealt " + damage + " damage to " + enemy.getLogName() + ".");

            if (RandomNumberGenerator.rollSucceeds(stunProbability))
            {
                enemy.getStatusEffects().freeze();
                LogRendererFactory.instance().log("Enemy stunned.");
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
            "Damage: " + (20 + 10 * spellPoints),
            "Chance to stun: " + 5 * spellPoints + "%"
        };
    }
}
