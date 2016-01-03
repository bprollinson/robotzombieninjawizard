package rznw.game.skill;

import rznw.map.GameWorld;

public abstract class PassiveSkill extends Skill
{
    public boolean canUse(GameWorld gameWorld)
    {
        return false;
    }

    public void use(GameWorld gameWorld)
    {
    }
}
