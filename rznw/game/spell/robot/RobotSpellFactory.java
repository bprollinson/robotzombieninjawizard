package rznw.game.spell.robot;

import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;

public class RobotSpellFactory extends SpellFactory
{
    private static final int SPELL_POWER_DOWN = 0;
    private static final int SPELL_ELECTRIC_FIELD = 1;
    private static final int SPELL_OVERLOAD = 2;
    private static final int SPELL_SUCK_POWER = 3;
    private static final int SPELL_ROCKET_PACK = 4;
    private static final int SPELL_ROCKET_JUMP = 5;
    private static final int SPELL_ROCKET_SHOT = 6;
    private static final int SPELL_PARALYZING_BLAST = 7;
    private static final int SPELL_GENETIC_TARGETING = 8;
    private static final int SPELL_LEVEL_DOWN = 9;
    private static final int SPELL_BOOST_GENETICS = 10;
    private static final int SPELL_LEVEL_UP = 11;
    private static final int SPELL_EXTRACT_WEAPON = 12;
    private static final int SPELL_MEAT_SHIELD = 13;
    private static final int SPELL_SIGNAL_WEAPON = 14;
    private static final int SPELL_POWER_SEARCH = 15;

    public Spell getSpell(int spellIndex)
    {
        switch (spellIndex)
        {
            case RobotSpellFactory.SPELL_POWER_DOWN:
                return new PowerDownSpell();
            case RobotSpellFactory.SPELL_ELECTRIC_FIELD:
                return new ElectricFieldSpell();
            case RobotSpellFactory.SPELL_OVERLOAD:
                return new OverloadSpell();
            case RobotSpellFactory.SPELL_SUCK_POWER:
                return new SuckPowerSpell();
            case RobotSpellFactory.SPELL_ROCKET_PACK:
                return new RocketPackSpell();
            case RobotSpellFactory.SPELL_ROCKET_JUMP:
                return new RocketJumpSpell();
            case RobotSpellFactory.SPELL_ROCKET_SHOT:
                return new RocketShotSpell();
            case RobotSpellFactory.SPELL_PARALYZING_BLAST:
                return new ParalyzingBlastSpell();
            case RobotSpellFactory.SPELL_GENETIC_TARGETING:
                return new GeneticTargetingSpell();
            case RobotSpellFactory.SPELL_LEVEL_DOWN:
                return new LevelDownSpell();
            case RobotSpellFactory.SPELL_BOOST_GENETICS:
                return new BoostGeneticsSpell();
            case RobotSpellFactory.SPELL_LEVEL_UP:
                return new LevelUpSpell();
            case RobotSpellFactory.SPELL_EXTRACT_WEAPON:
                return new ExtractWeaponSpell();
            case RobotSpellFactory.SPELL_MEAT_SHIELD:
                return new MeatShieldSpell();
            case RobotSpellFactory.SPELL_SIGNAL_WEAPON:
                return new SignalWeaponSpell();
            case RobotSpellFactory.SPELL_POWER_SEARCH:
                return new PowerSearchSpell();
            default:
                return null;
        }
    }
}
