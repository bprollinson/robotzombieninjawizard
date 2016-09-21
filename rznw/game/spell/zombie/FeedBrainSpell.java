package rznw.game.spell.zombie;

import rznw.game.Character;
import rznw.game.StatusEffects;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;

public class FeedBrainSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Feed Brain";
    }

    public String getDescription()
    {
        return "Deals damage to an enemy and temporarily increases your magic power. Has the same range as melee combat.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Feed Brain");
        MainCharacter character = gameWorld.getMainCharacter();
        Map map = gameWorld.getMap();

        MapElement characterElement = character.getMapElement();

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(0, 0, direction);

        int row = characterElement.getRow() + positionChange.getDeltaRow();
        int column = characterElement.getColumn() + positionChange.getDeltaColumn();

        MapElement element = map.getElement(row, column);
        if (element != null && element.isEnemy())
        {
            int damage = 50 + 10 * spellPoints;

            Character enemy = ((EnemyMapElement)element).getCharacter();

            System.out.println("Before: " + enemy.getHP());
            enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("After: " + enemy.getHP());

            int numTurns = 2 + (int)Math.floor(spellPoints / 4);
            character.getStatusEffects().setStatusEffectTurns(StatusEffects.EFFECT_FEED_BRAIN, numTurns);
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
            "Additional magic damage: 60%"
        };
    }
}
