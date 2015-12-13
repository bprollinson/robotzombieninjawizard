package rznw.game.spell.robot;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;

public class PowerDownSpell extends UndirectedSpell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Power Down");

        int turnsToSkip = Math.max(5 - (int)Math.floor(spellPoints / 4), 1);
        int healHP = 30 * spellPoints;

        System.out.println("Skipping " + turnsToSkip + " turns to heal " + healHP + " hp");

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().skipTurns(turnsToSkip);
        character.heal(healHP);
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}
