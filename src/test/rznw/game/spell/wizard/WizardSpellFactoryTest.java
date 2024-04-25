package rznw.game.spell.wizard;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.api.Test;

import rznw.game.spell.Spell;

class WizardSpellFactoryTest
{
    @Test
    public void testGetSpellCreatesRockWall()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_ROCK_WALL);

        assertInstanceOf(RockWallSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesMeteorShower()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_METEOR_SHOWER);

        assertInstanceOf(MeteorShowerSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesEarthquake()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_EARTHQUAKE);

        assertInstanceOf(EarthquakeSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesSummonGolem()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_SUMMON_GOLEM);

        assertInstanceOf(SummonGolemSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesRingOfFire()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_RING_OF_FIRE);

        assertInstanceOf(RingOfFireSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesFireball()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_FIREBALL);

        assertInstanceOf(FireballSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesHeatRay()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_HEAT_RAY);

        assertInstanceOf(HeatRaySpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesArcLightning()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_ARC_LIGHTNING);

        assertInstanceOf(ArcLightningSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesRepel()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_REPEL);

        assertInstanceOf(RepelSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesTeleport()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_TELEPORT);

        assertInstanceOf(TeleportSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesRicochetBlast()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_RICOCHET_BLAST);

        assertInstanceOf(RicochetBlastSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesUpdraft()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_UPDRAFT);

        assertInstanceOf(UpdraftSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesIceField()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_ICE_FIELD);

        assertInstanceOf(IceFieldSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesHeal()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_HEAL);

        assertInstanceOf(HealSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesCleanse()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_CLEANSE);

        assertInstanceOf(CleanseSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesVitalZap()
    {
        WizardSpellFactory factory = new WizardSpellFactory();
        Spell spell = factory.getSpell(WizardSpellFactory.SPELL_VITAL_ZAP);

        assertInstanceOf(VitalZapSpell.class, spell);
    }
}
