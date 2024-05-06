package rznw.game.spell.robot;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.api.Test;

import rznw.game.spell.Spell;

class RobotSpellFactoryTest
{
    @Test
    public void testGetSpellCreatesPowerDown()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_POWER_DOWN);

        assertInstanceOf(PowerDownSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesElectricField()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_ELECTRIC_FIELD);

        assertInstanceOf(ElectricFieldSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesOverload()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_OVERLOAD);

        assertInstanceOf(OverloadSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesSuckPower()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_SUCK_POWER);

        assertInstanceOf(SuckPowerSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesRocketPack()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_ROCKET_PACK);

        assertInstanceOf(RocketPackSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesRocketJump()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_ROCKET_JUMP);

        assertInstanceOf(RocketJumpSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesRocketShot()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_ROCKET_SHOT);

        assertInstanceOf(RocketShotSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesParalyzingBlast()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_PARALYZING_BLAST);

        assertInstanceOf(ParalyzingBlastSpell.class, spell);
    }

    @Test
    public void testGetSpellGeneticTargeting()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_GENETIC_TARGETING);

        assertInstanceOf(GeneticTargetingSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesLevelDown()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_LEVEL_DOWN);

        assertInstanceOf(LevelDownSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesBoostGenetics()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_BOOST_GENETICS);

        assertInstanceOf(BoostGeneticsSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesLevelUp()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_LEVEL_UP);

        assertInstanceOf(LevelUpSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesExtractWeapon()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_EXTRACT_WEAPON);

        assertInstanceOf(ExtractWeaponSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesMeatShield()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_MEAT_SHIELD);

        assertInstanceOf(MeatShieldSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesSignalWeapon()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_SIGNAL_WEAPON);

        assertInstanceOf(SignalWeaponSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesPowerSearch()
    {
        RobotSpellFactory factory = new RobotSpellFactory();
        Spell spell = factory.getSpell(RobotSpellFactory.SPELL_POWER_SEARCH);

        assertInstanceOf(PowerSearchSpell.class, spell);
    }
}
