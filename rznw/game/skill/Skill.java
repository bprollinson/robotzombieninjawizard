package rznw.game.skill;

import rznw.map.GameWorld;

public abstract class Skill
{
    public abstract boolean canUse(GameWorld gameWorld);
    public abstract void use(GameWorld gameWorld);
}
