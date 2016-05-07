package rznw.game.spell.robot;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;

public class PowerSearchSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Power Search";
    }

    public String getDescription()
    {
        return "Temporarily increases the power level of weapons and armor dropped by enemies.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Power Search");
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {};
    }
}
