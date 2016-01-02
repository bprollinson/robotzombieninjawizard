package rznw.game.stat;

public class StatFactory
{
    public Stat getStat(int statIndex)
    {
        switch(statIndex)
        {
            case 0:
                return new HealthStat();
            default:
                return null;
        }
    }
}
