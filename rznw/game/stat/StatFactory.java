package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class StatFactory
{
    public Stat getStat(int statIndex)
    {
        switch(statIndex)
        {
            case MainCharacter.STAT_HEALTH:
                return new HealthStat();
            case MainCharacter.STAT_PHYSICAL_REGENERATION:
                return new PhysicalRegenerationStat();
            case MainCharacter.STAT_LAST_BREATH:
                return new LastBreathStat();
            case MainCharacter.STAT_LIFE_BOND:
                return new LifeBondStat();
            case MainCharacter.STAT_ACCURACY:
                return new AccuracyStat();
            case MainCharacter.STAT_DODGE:
                return new DodgeStat();
            case MainCharacter.STAT_SIGHT:
                return new SightStat();
            case MainCharacter.STAT_FIND_TRAPS:
                return new FindTrapsStat();
            case MainCharacter.STAT_DAMAGE:
                return new DamageStat();
            case MainCharacter.STAT_PADDING:
                return new PaddingStat();
            case MainCharacter.STAT_UNENCUMBERANCE:
                return new UnencumberanceStat();
            case MainCharacter.STAT_THICK_SKIN:
                return new ThickSkinStat();
            case MainCharacter.STAT_MANA:
                return new ManaStat();
            case MainCharacter.STAT_MENTAL_REGENERATION:
                return new MentalRegenerationStat();
            case MainCharacter.STAT_MANA_BURN:
                return new ManaBurnStat();
            case MainCharacter.STAT_MAGIC_RESISTANCE:
                return new MagicResistanceStat();
            default:
                return null;
        }
    }
}
