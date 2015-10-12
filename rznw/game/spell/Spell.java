package rznw.game.spell;

import rznw.game.maincharacter.MainCharacter;

public abstract class Spell
{
    public abstract boolean canCast(MainCharacter character);
    public abstract void cast(MainCharacter character);
}
