package rznw.game;

import rznw.game.Character;

public class StatusEffects
{
    private static final int POISON_DAMAGE = 10;

    boolean frozen = false;
    boolean poisoned = false;

    public void freeze()
    {
        this.frozen = true;
    }

    public void poison()
    {
        this.poisoned = true;
    }

    public boolean isFrozen()
    {
        return this.frozen;
    }

    public void processTurn(Character character)
    {
        this.frozen = false;

        if (this.poisoned)
        {
            System.out.println("Damaging enemy due to poison");
            character.damage(StatusEffects.POISON_DAMAGE);
        }
    }
}
