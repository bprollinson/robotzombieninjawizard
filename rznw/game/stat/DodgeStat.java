package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class DodgeStat extends Stat
{
    public String[] getStats(MainCharacter character, int statPoints)
    {
        int toDodgePercent = 2 * statPoints;

        return new String[] {
            "To dodge: " + toDodgePercent + "%"
        };
    }
}
