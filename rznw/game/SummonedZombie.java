package rznw.game;

public class SummonedZombie extends SummonedCharacter
{
    private int maxHP;

    public SummonedZombie(int maxHP)
    {
        this.maxHP = maxHP;
        this.HP = maxHP;
    }

    public int getMaxHP()
    {
        return this.maxHP;
    }
}
