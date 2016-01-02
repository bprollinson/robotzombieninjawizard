package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class DamageStat extends Stat
{
    public String[] getStats(MainCharacter character, int statPoints)
    {
        int damage = 50 + 5 * statPoints;

        return new String[] {
            "Damage: " + damage
        };
    }
}
