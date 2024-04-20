package rznw.game.spell.ninja;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class CounterstrikeSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Counterstrike";
    }

    public String getDescription()
    {
        return "The next time you are damaged by an enemy, you have a chance to auto-attack that enemy, dealing damage.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting counterstrike.");

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_COUNTERSTRIKE, true);
        LogRendererFactory.instance().log("Counterstrike enabled.");
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Counterstrike probability: " + Math.min(10 * spellPoints, 100) + "%",
            "Counterstrike damage: " + 10 * spellPoints,
            "Number of turns: 1"
        };
    }
}
