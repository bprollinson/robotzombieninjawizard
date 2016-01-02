package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class HealthStat extends Stat
{
    public String[] getStats(MainCharacter character, int statPoints)
    {
        int hp = 200 + 20 * statPoints;

        return new String[] {
            "HP: " + hp
        };
    }
}
