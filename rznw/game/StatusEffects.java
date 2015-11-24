package rznw.game;

import rznw.game.Character;

public class StatusEffects
{
    private static final int POISON_DAMAGE = 10;

    boolean frozen = false;
    boolean poisoned = false;
    boolean isReversingPain = false;
    boolean deathStriking = false;

    public void freeze()
    {
        this.frozen = true;
    }

    public void poison()
    {
        this.poisoned = true;
    }

    public void reversePain()
    {
        this.isReversingPain = true;
    }

    public boolean isFrozen()
    {
        return this.frozen;
    }

    public boolean isReversingPain()
    {
        return this.isReversingPain;
    }

    public void enableDeathStrike()
    {
        this.deathStriking = true;
    }

    public boolean isDeathStriking()
    {
        return this.deathStriking;
    }

    public void disableDeathStrike()
    {
        this.deathStriking = false;
    }

    public void processTurn(Character character)
    {
        this.frozen = false;

        if (this.poisoned)
        {
            System.out.println("Damaging enemy due to poison");
            character.damage(StatusEffects.POISON_DAMAGE, null);
        }

        this.isReversingPain = false;
    }
}
