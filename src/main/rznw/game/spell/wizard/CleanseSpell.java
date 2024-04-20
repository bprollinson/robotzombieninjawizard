package rznw.game.spell.wizard;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class CleanseSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Cleanse";
    }

    public String getDescription()
    {
        return "Has a chance to heal all status effects currently affecting you. MP cost decreases and chance to succeed increases as spell level increases.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting cleanse.");
        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_POISONED, false);
        character.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_CONFUSION, 0);
        LogRendererFactory.instance().log("Healed status effects.");
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints)
        };
    }
}
