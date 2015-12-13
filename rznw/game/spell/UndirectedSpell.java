package rznw.game.spell;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public abstract class UndirectedSpell extends Spell
{
    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
    }

    public boolean requiresDirectionInput()
    {
        return false;
    }
}
