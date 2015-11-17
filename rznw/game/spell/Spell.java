package rznw.game.spell;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public abstract class Spell
{
    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_DOWN = 1;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 3;

    public abstract void cast(GameWorld gameWorld, int spellPoints);
    public abstract int getMPCost(MainCharacter character, int spellPoints);

    public boolean canCast(MainCharacter character, int spellPoints)
    {
        return spellPoints > 0 && character.getMP() >= this.getMPCost(character, spellPoints);
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        this.cast(gameWorld, spellPoints);
    }

    public boolean requiresDirectionInput()
    {
        return false;
    }
}
