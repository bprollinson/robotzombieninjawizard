package rznw.game.spell.wizard;

import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;

public class WizardSpellFactory extends SpellFactory
{
    private static final int SPELL_ROCK_WALL = 0;
    private static final int SPELL_METEOR_SHOWER = 1;
    private static final int SPELL_EARTHQUAKE = 2;
    private static final int SPELL_SUMMON_GOLEM = 3;
    public static final int SPELL_RING_OF_FIRE = 4;
    private static final int SPELL_FIREBALL = 5;
    private static final int SPELL_HEAT_RAY = 6;
    private static final int SPELL_ARC_LIGHTNING = 7;
    private static final int SPELL_REPEL = 8;
    private static final int SPELL_TELEPORT = 9;
    private static final int SPELL_RICOCHET_BLAST = 10;
    private static final int SPELL_UPDRAFT = 11;
    private static final int SPELL_ICE_FIELD = 12;
    private static final int SPELL_HEAL = 13;
    private static final int SPELL_CLEANSE = 14;
    private static final int SPELL_VITAL_ZAP = 15;

    public Spell getSpell(int spellIndex)
    {
        switch (spellIndex)
        {
            case WizardSpellFactory.SPELL_ROCK_WALL:
                return new RockWallSpell();
            case WizardSpellFactory.SPELL_METEOR_SHOWER:
                return new MeteorShowerSpell();
            case WizardSpellFactory.SPELL_EARTHQUAKE:
                return new EarthquakeSpell();
            case WizardSpellFactory.SPELL_SUMMON_GOLEM:
                return new SummonGolemSpell();
            case WizardSpellFactory.SPELL_RING_OF_FIRE:
                return new RingOfFireSpell();
            case WizardSpellFactory.SPELL_FIREBALL:
                return new FireballSpell();
            case WizardSpellFactory.SPELL_HEAT_RAY:
                return new HeatRaySpell();
            case WizardSpellFactory.SPELL_ARC_LIGHTNING:
                return new ArcLightningSpell();
            case WizardSpellFactory.SPELL_REPEL:
                return new RepelSpell();
            case WizardSpellFactory.SPELL_TELEPORT:
                return new TeleportSpell();
            case WizardSpellFactory.SPELL_RICOCHET_BLAST:
                return new RicochetBlastSpell();
            case WizardSpellFactory.SPELL_UPDRAFT:
                return new UpdraftSpell();
            case WizardSpellFactory.SPELL_ICE_FIELD:
                return new IceFieldSpell();
            case WizardSpellFactory.SPELL_HEAL:
                return new HealSpell();
            case WizardSpellFactory.SPELL_CLEANSE:
                return new CleanseSpell();
            case WizardSpellFactory.SPELL_VITAL_ZAP:
                return new VitalZapSpell();
            default:
                return null;
        }
    }
}
