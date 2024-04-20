package rznw.game.spell.zombie;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class InfectiousRageSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Infectious Rage";
    }

    public String getDescription()
    {
        return "Increase all damage you deal for a period time. Using this spell will poison you.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting infectious rage.");

        MainCharacter character = gameWorld.getMainCharacter();

        character.getStatusEffects().poison();
        int numTurns = 2 + (int)Math.floor(spellPoints / 4);
        character.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_INFECTIOUS_RAGE, numTurns);
        LogRendererFactory.instance().log("Infectious rage enabled.");
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
            "Additional damage: 50%",
            "Turns: " + numTurns,
            "Poison damage to you: 10 per turn"
        };
    }
}
