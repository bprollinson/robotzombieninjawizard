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
    public abstract void cast(GameWorld gameWorld, int spellPoints, int direction);
    public abstract int getMPCost(MainCharacter character, int spellPoints);
    public abstract boolean requiresDirectionInput();
    //public abstract String[] getStats(MainCharacter character, int spellPoints);

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "line 1",
            "line 2"
        };
    }

    public boolean canCast(GameWorld gameWorld, int spellPoints)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        return spellPoints > 0 && character.getMP() >= this.getMPCost(character, spellPoints);
    }
}
