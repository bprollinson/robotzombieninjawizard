package rznw.game;

public class SummonedGolem extends SummonedCharacter
{
    private static final int SUMMON_NUMBER = 1;

    private int maxHP;

    public SummonedGolem(int maxHP)
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
        return SummonedGolem.SUMMON_NUMBER;
    }
}
