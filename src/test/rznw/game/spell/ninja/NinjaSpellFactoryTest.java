package rznw.game.spell.ninja;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.api.Test;

import rznw.game.spell.Spell;

public class NinjaSpellFactoryTest
{
    @Test
    public void testGetSpellCreatesStunStrike()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_STUN_STRIKE);

        assertInstanceOf(StunStrikeSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesRoundhouseStrike()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_ROUNDHOUSE_STRIKE);

        assertInstanceOf(RoundhouseStrikeSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesPoisonStrike()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_POISON_STRIKE);

        assertInstanceOf(PoisonStrikeSpell.class, spell);
    }
 
    @Test
    public void testGetSpellCreatesArmorBreak()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_ARMOR_BREAK);

        assertInstanceOf(ArmorBreakSpell.class, spell);
    }
 
    @Test
    public void testGetSpellCreatesPinStrike()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_PIN_STRIKE);

        assertInstanceOf(PinStrikeSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesShurikenStar()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_SHURIKEN_STAR);

        assertInstanceOf(ShurikenStarSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesSmokeCluster()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_SMOKE_CLUSTER);

        assertInstanceOf(SmokeClusterSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesStunBomb()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_STUN_BOMB);

        assertInstanceOf(StunBombSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesStealGold()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_STEAL_GOLD);

        assertInstanceOf(StealGoldSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesStealItem()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_STEAL_ITEM);

        assertInstanceOf(StealItemSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesStealEquipment()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_STEAL_EQUIPMENT);

        assertInstanceOf(StealEquipmentSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesStealExperience()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_STEAL_EXPERIENCE);

        assertInstanceOf(StealExperienceSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesSmokeBomb()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_SMOKE_BOMB);

        assertInstanceOf(SmokeBombSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesCounterstrike()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_COUNTERSTRIKE);

        assertInstanceOf(CounterstrikeSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesReversePain()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_REVERSE_PAIN);

        assertInstanceOf(ReversePainSpell.class, spell);
    }

    @Test
    public void testGetSpellCreatesDeathStrike()
    {
        NinjaSpellFactory factory = new NinjaSpellFactory();
        Spell spell = factory.getSpell(NinjaSpellFactory.SPELL_DEATH_STRIKE);

        assertInstanceOf(DeathStrikeSpell.class, spell);
    }
}
