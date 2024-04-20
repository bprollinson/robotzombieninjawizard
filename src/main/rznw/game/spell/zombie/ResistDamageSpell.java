package rznw.game.spell.zombie;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class ResistDamageSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Resist Damage";
    }

    public String getDescription()
    {
        return "Reduce all damage you take for a period of time by increasing your defense. This affects all types of damage.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting resist damage.");

        MainCharacter character = gameWorld.getMainCharacter();
        int numTurns = 1 + (int)Math.floor(spellPoints / 4);

        character.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_RESIST_DAMAGE, numTurns);
        LogRendererFactory.instance().log("Resist damage enabled.");
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
            "Damage padding: " + 2 * spellPoints + "%"
        };
    }
}
