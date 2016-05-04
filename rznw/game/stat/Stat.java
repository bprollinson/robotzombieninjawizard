package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public abstract class Stat
{
    public abstract String getDisplayName();

    public abstract String getDescription();

    public abstract String[] getStats(MainCharacter character, int statPoints);
}
