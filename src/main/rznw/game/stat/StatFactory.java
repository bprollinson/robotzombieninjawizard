package rznw.game.stat;

public class StatFactory
{
    public Stat getStat(int statIndex)
    {
        switch(statIndex)
        {
            case Stat.STAT_HEALTH:
                return new HealthStat();
            case Stat.STAT_PHYSICAL_REGENERATION:
                return new PhysicalRegenerationStat();
            case Stat.STAT_LAST_BREATH:
                return new LastBreathStat();
            case Stat.STAT_LIFE_BOND:
                return new LifeBondStat();
            case Stat.STAT_ACCURACY:
                return new AccuracyStat();
            case Stat.STAT_DODGE:
                return new DodgeStat();
            case Stat.STAT_SIGHT:
                return new SightStat();
            case Stat.STAT_FIND_TRAPS:
                return new FindTrapsStat();
            case Stat.STAT_DAMAGE:
                return new DamageStat();
            case Stat.STAT_PADDING:
                return new PaddingStat();
            case Stat.STAT_UNENCUMBERANCE:
                return new UnencumberanceStat();
            case Stat.STAT_THICK_SKIN:
                return new ThickSkinStat();
            case Stat.STAT_MANA:
                return new ManaStat();
            case Stat.STAT_MENTAL_REGENERATION:
                return new MentalRegenerationStat();
            case Stat.STAT_MANA_BURN:
                return new ManaBurnStat();
            case Stat.STAT_MAGIC_RESISTANCE:
                return new MagicResistanceStat();
            default:
                return null;
        }
    }
}
