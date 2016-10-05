package rznw.game.spell.zombie;

import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;

public class ZombieSpellFactory extends SpellFactory
{
    private static final int SPELL_POISON_SKIN = 0;
    public static final int SPELL_RESIST_DAMAGE = 1;
    public static final int SPELL_THORN_SKIN = 2;
    private static final int SPELL_BARBED_SKIN = 3;
    private static final int SPELL_FEED_FLESH = 4;
    private static final int SPELL_FEED_BRAIN = 5;
    private static final int SPELL_FEED_PAST = 6;
    private static final int SPELL_FEED_MIND = 7;
    private static final int SPELL_LOCUST_SWARM = 8;
    private static final int SPELL_POISON_CLOUD = 9;
    private static final int SPELL_INFECTIOUS_RAGE = 10;
    private static final int SPELL_BLOTCH = 11;
    private static final int SPELL_SUMMON_ZOMBIE = 12;
    private static final int SPELL_INFER_ZOMBIE = 13;
    private static final int SPELL_MULTIPLY_ZOMBIES = 14;
    private static final int SPELL_EXPLODE_ZOMBIES = 15;

    public Spell getSpell(int spellIndex)
    {
        switch (spellIndex)
        {
            case ZombieSpellFactory.SPELL_POISON_SKIN:
                return new PoisonSkinSpell();
            case ZombieSpellFactory.SPELL_RESIST_DAMAGE:
                return new ResistDamageSpell();
            case ZombieSpellFactory.SPELL_THORN_SKIN:
                return new ThornSkinSpell();
            case ZombieSpellFactory.SPELL_BARBED_SKIN:
                return new BarbedSkinSpell();
            case ZombieSpellFactory.SPELL_FEED_FLESH:
                return new FeedFleshSpell();
            case ZombieSpellFactory.SPELL_FEED_BRAIN:
                return new FeedBrainSpell();
            case ZombieSpellFactory.SPELL_FEED_PAST:
                return new FeedPastSpell();
            case ZombieSpellFactory.SPELL_FEED_MIND:
                return new FeedMindSpell();
            case ZombieSpellFactory.SPELL_LOCUST_SWARM:
                return new LocustSwarmSpell();
            case ZombieSpellFactory.SPELL_POISON_CLOUD:
                return new PoisonCloudSpell();
            case ZombieSpellFactory.SPELL_INFECTIOUS_RAGE:
                return new InfectiousRageSpell();
            case ZombieSpellFactory.SPELL_BLOTCH:
                return new BlotchSpell();
            case ZombieSpellFactory.SPELL_SUMMON_ZOMBIE:
                return new SummonZombieSpell();
            case ZombieSpellFactory.SPELL_INFER_ZOMBIE:
                return new InferZombieSpell();
            case ZombieSpellFactory.SPELL_MULTIPLY_ZOMBIES:
                return new MultiplyZombiesSpell();
            case ZombieSpellFactory.SPELL_EXPLODE_ZOMBIES:
                return new ExplodeZombiesSpell();
            default:
                return null;
        }
    }
}
