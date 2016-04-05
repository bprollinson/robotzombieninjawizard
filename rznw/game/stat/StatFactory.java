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
            case 4:
                return new AccuracyStat();
            case 5:
                return new DodgeStat();
            case 6:
                return new SightStat();
            case 7:
                return new FindTrapsStat();
            case 8:
                return new DamageStat();
            case 9:
                return new PaddingStat();
            case 10:
                return new UnencumberanceStat();
            case 11:
                return new ThickSkinStat();
            case 12:
                return new ManaStat();
            case 13:
                return new MentalRegenerationStat();
            case 14:
                return new ManaBurnStat();
            case 15:
                return new MagicResistanceStat();
            default:
                return null;
        }
    }
}
