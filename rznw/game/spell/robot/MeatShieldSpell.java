package rznw.game.spell.robot;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;

public class MeatShieldSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Meat Shield";
    }

    public String getDescription()
    {
        return "Damages a nearby enemy. If this attack kills that enemy, your defense is temporarily increased.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Meat Shield");

        MainCharacter character = gameWorld.getMainCharacter();
        MapElement characterElement = character.getMapElement();
        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(characterElement.getRow(), characterElement.getColumn(), direction);

        Map map = gameWorld.getMap();
        MapElement element = map.getElement(positionChange.getFinalRow(), positionChange.getFinalColumn());
        if (element != null && element.isEnemy())
        {
            int damage = 50 + 10 * spellPoints;

            Character enemy = ((EnemyMapElement)element).getCharacter();

            System.out.println("Before: " + enemy.getHP());
            enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("After: " + enemy.getHP());

            if (enemy.isDead())
            {
                character.getStatusEffects().enableMeatShield(1 + spellPoints, 5 * spellPoints, 5 * spellPoints);
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
            "Damage: " + damage,
            "Turns: " + (1 + spellPoints),
            "Bonus padding: " + 5 * spellPoints + "%",
            "Bonus dodge: " + 5 * spellPoints + "%"
        };
    }
}
