package rznw.game.spell.robot;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.StatusEffects;
import rznw.map.GameWorld;

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
        System.out.println("Casting Power Down");

        int turnsToSkip = Math.max(5 - (int)Math.floor(spellPoints / 4), 1);
        int healHP = 30 * spellPoints;

        System.out.println("Skipping " + turnsToSkip + " turns to heal " + healHP + " hp");

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().setStatusEffectTurns(StatusEffects.EFFECT_SKIP, turnsToSkip);
        character.heal(healHP);
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
