package rznw.game.spell.robot;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class PowerDownSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Power Down";
    }

    public String getDescription()
    {
        return "Powers down your systems to regenerate lost health.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting power down.");

        int turnsToSkip = Math.max(5 - (int)Math.floor(spellPoints / 4), 1);
        int healHP = 30 * spellPoints;

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_SKIP, turnsToSkip);
        int HPHealed = character.heal(healHP);
        LogRendererFactory.instance().log("You healed " + HPHealed + " HP.");
        LogRendererFactory.instance().log("Powered down");
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int turnsToSkip = Math.max(5 - (int)Math.floor(spellPoints / 4), 1);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "HP healed: " + 30 * spellPoints,
            "Turns skipped: " + turnsToSkip
        };
    }
}
