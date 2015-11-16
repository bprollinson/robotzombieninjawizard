package rznw.game.spell;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public abstract class Spell
{
    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_DOWN = 1;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 3;

    public abstract boolean canCast(MainCharacter character);
    public abstract void cast(GameWorld gameWorld);
    public abstract int getMPCost(MainCharacter character);

    public void cast(GameWorld gameWorld, int direction)
    {
        this.cast(gameWorld);
    }

    public boolean requiresDirectionInput()
    {
        return false;
    }
}
