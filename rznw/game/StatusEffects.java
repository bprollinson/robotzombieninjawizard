package rznw.game;

import rznw.game.Character;
import rznw.map.GameWorld;

public class StatusEffects
{
    private static final int POISON_DAMAGE = 10;

    boolean frozen = false;
    boolean poisoned = false;
    boolean isReversingPain = false;
    boolean deathStriking = false;
    boolean smokeBombEnabled = false;
    boolean counterstriking = false;
    int confuseTurns = 0;
    int signalWeaponTurns = 0;
    int thornSkinTurns = 0;

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

    public void enableSmokeBomb()
    {
        this.smokeBombEnabled = true;
    }

    public boolean smokeBombEnabled()
    {
        return this.smokeBombEnabled;
    }

    public void disableSmokeBomb()
    {
        this.smokeBombEnabled = false;
    }

    public void enableCounterstrike()
    {
        this.counterstriking = true;
    }

    public boolean isCounterstriking()
    {
        return this.counterstriking;
    }

    public void disableCounterstrike()
    {
        this.counterstriking = false;
    }

    public void confuse()
    {
        this.confuseTurns = 3;
    }

    public boolean isConfused()
    {
        return this.confuseTurns > 0;
    }

    public void enableSignalWeapon(int numTurns)
    {
        this.signalWeaponTurns = numTurns;
    }

    public boolean signalWeaponEnabled()
    {
        return this.signalWeaponTurns > 0;
    }

    public void enableThornSkin(int numTurns)
    {
        this.thornSkinTurns = numTurns;
    }

    public boolean thornSkinEnabled()
    {
        return this.thornSkinTurns > 0;
    }

    public void processTurn(Character character, GameWorld gameWorld)
    {
        this.frozen = false;

        if (this.poisoned)
        {
            System.out.println("Damaging enemy due to poison");
            character.damage(StatusEffects.POISON_DAMAGE, null, gameWorld);
        }

        this.isReversingPain = false;

        if (this.confuseTurns > 0)
        {
            this.confuseTurns--;
        }

        if (this.signalWeaponTurns > 0)
        {
            System.out.println("Used up one turn of signal weapon");
            this.signalWeaponTurns--;
        }

        if (this.thornSkinTurns > 0)
        {
            this.thornSkinTurns--;
        }
    }
}
