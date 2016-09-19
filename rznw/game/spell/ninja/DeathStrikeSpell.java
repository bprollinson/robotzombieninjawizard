package rznw.game.spell.ninja;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.StatusEffects;
import rznw.map.GameWorld;

public class DeathStrikeSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Death Strike";
    }

    public String getDescription()
    {
        return "The next time you are damaged by an enemy, you have a chance to auto-kill that enemy.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Death Strike");

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().setStatusEffect(StatusEffects.EFFECT_DEATH_STRIKE, true);
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Chance to auto-kill: " + 5 * spellPoints + "%",
        };
    }
}
