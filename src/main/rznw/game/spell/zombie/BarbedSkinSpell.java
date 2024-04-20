package rznw.game.spell.zombie;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class BarbedSkinSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Barbed Skin";
    }

    public String getDescription()
    {
        return "Enemies have a chance to become stunned when physically attacking you for a period of time. This does not affect ranged attacks.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting barbed skin.");

        MainCharacter character = gameWorld.getMainCharacter();
        int numTurns = 1 + (int)Math.floor(spellPoints / 4);

        character.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_BARBED_SKIN, numTurns);
        LogRendererFactory.instance().log("Barbed skin enabled.");
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int numTurns = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Number of turns: " + numTurns,
            "Chance to stun: " + 5 * spellPoints + "%"
        };
    }
}
