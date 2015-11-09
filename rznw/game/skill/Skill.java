package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public abstract class Skill
{
    public abstract boolean canUse(MainCharacter character);
    public abstract void use(GameWorld gameWorld);
}
