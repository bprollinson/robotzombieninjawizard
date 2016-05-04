package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class DamageStat extends Stat
{
    public String getDisplayName()
    {
        return "Damage";
    }

    public String getDescription()
    {
        return "Increases your damage in melee combat.";
    }

    public String[] getStats(MainCharacter character, int statPoints)
    {
        int damage = 50 + 5 * statPoints;

        return new String[] {
            "Damage: " + damage
        };
    }
}
