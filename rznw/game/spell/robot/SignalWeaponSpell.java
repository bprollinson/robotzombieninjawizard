package rznw.game.spell.robot;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;

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
        System.out.println("Casting Signal Weapon");

        int numTurns = 2 + (int)Math.floor(spellPoints / 4);

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().enableSignalWeapon(numTurns);
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
