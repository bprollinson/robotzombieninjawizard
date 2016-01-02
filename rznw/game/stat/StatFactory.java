package rznw.game.stat;

public class StatFactory
{
    public Stat getStat(int statIndex)
    {
        switch(statIndex)
        {
            case 0:
                return new HealthStat();
            case 1:
                return new PhysicalRegenerationStat();
            case 2:
                return new LastBreathStat();
            case 3:
                return new LifeBondStat();
            default:
                return null;
        }
    }
}
