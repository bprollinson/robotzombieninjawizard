package rznw.game.maincharacter;

import rznw.game.Character;

public abstract class MainCharacter extends Character
{
    public int getMaxHP()
    {
        return 1000;
    }

    public int getDamage()
    {
        return 5;
    }
}
