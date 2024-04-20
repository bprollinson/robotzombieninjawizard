package rznw.game.spell.ninja;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

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
        LogRendererFactory.instance().log("Casting death strike.");

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_DEATH_STRIKE, true);
        LogRendererFactory.instance().log("Death strike enabled.");
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
