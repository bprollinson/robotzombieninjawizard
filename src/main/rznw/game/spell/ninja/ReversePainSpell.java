package rznw.game.spell.ninja;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class ReversePainSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Reverse Pain";
    }

    public String getDescription()
    {
        return "Until your next turn, damage you would receive from enemies is converted into restored HP.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting reverse pain.");

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_REVERSE_PAIN, true);
        LogRendererFactory.instance().log("Reverse pain enabled.");
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage taken: 0",
            "HP healed: 100% of enemy damage",
            "Number of turns: 1"
        };
    }
}
