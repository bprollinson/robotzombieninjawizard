package rznw.game.spell;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public abstract class Spell
{
    public abstract boolean canCast(MainCharacter character);
    public abstract void cast(GameWorld gameWorld);
}
