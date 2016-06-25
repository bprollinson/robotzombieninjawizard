package rznw.game;

public class SummonedZombie extends SummonedCharacter
{
    public static final int SUMMON_NUMBER = 2;

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

    public int getSummonNumber()
    {
        return SummonedZombie.SUMMON_NUMBER;
    }
}
