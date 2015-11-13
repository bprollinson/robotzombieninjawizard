package rznw.game;

public class StatusEffects
{
    boolean frozen = false;

    public void freeze()
    {
        this.frozen = true;
    }

    public boolean isFrozen()
    {
        return this.frozen;
    }

    public void processTurn()
    {
        this.frozen = false;
    }
}
