package rznw.game.spell.robot;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class SignalWeaponSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Signal Weapon";
    }

    public String getDescription()
    {
        return "Temporarily grants you the possibility of confusing enemies during melee combat.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting signal weapon.");

        int numTurns = 2 + (int)Math.floor(spellPoints / 4);

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_SIGNAL_WEAPON, numTurns);
        LogRendererFactory.instance().log("Signal weapon enabled.");
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int numTurns = 2 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Number of turns: " + numTurns,
            "Chance to confuse: " + 5 * spellPoints + "%"
        };
    }
}
