package rznw.game.spell;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public abstract class DirectedSpell extends Spell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
    }

    public boolean requiresDirectionInput()
    {
        return true;
    }
}
