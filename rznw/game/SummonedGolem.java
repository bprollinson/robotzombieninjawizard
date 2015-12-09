package rznw.game;

public class SummonedGolem extends SummonedCharacter
{
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
}
