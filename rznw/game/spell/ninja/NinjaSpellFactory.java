package rznw.game.spell.ninja;

import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;

public class NinjaSpellFactory extends SpellFactory
{
    private static final int SPELL_STUN_STRIKE = 0;
    private static final int SPELL_ROUNDHOUSE_STRIKE = 1;
    private static final int SPELL_POISON_STRIKE = 2;
    private static final int SPELL_ARMOR_BREAK = 3;
    private static final int SPELL_PIN_STRIKE = 4;
    private static final int SPELL_SHURIKEN_STAR = 5;
    private static final int SPELL_SMOKE_CLUSTER = 6;
    private static final int SPELL_STUN_BOMB = 7;
    private static final int SPELL_STEAL_GOLD = 8;
    private static final int SPELL_STEAL_ITEM = 9;
    private static final int SPELL_STEAL_EQUIPMENT = 10;
    private static final int SPELL_STEAL_EXPERIENCE = 11;
    public static final int SPELL_SMOKE_BOMB = 12;
    public static final int SPELL_COUNTERSTRIKE = 13;
    private static final int SPELL_REVERSE_PAIN = 14;
    public static final int SPELL_DEATH_STRIKE = 15;

    public Spell getSpell(int spellIndex)
    {
        switch (spellIndex)
        {
            case NinjaSpellFactory.SPELL_STUN_STRIKE:
                return new StunStrikeSpell();
            case NinjaSpellFactory.SPELL_ROUNDHOUSE_STRIKE:
                return new RoundhouseStrikeSpell();
            case NinjaSpellFactory.SPELL_POISON_STRIKE:
                return new PoisonStrikeSpell();
            case NinjaSpellFactory.SPELL_ARMOR_BREAK:
                return new ArmorBreakSpell();
            case NinjaSpellFactory.SPELL_PIN_STRIKE:
                return new PinStrikeSpell();
            case NinjaSpellFactory.SPELL_SHURIKEN_STAR:
                return new ShurikenStarSpell();
            case NinjaSpellFactory.SPELL_SMOKE_CLUSTER:
                return new SmokeClusterSpell();
            case NinjaSpellFactory.SPELL_STUN_BOMB:
                return new StunBombSpell();
            case NinjaSpellFactory.SPELL_STEAL_GOLD:
                return new StealGoldSpell();
            case NinjaSpellFactory.SPELL_STEAL_ITEM:
                return new StealItemSpell();
            case NinjaSpellFactory.SPELL_STEAL_EQUIPMENT:
                return new StealEquipmentSpell();
            case NinjaSpellFactory.SPELL_STEAL_EXPERIENCE:
                return new StealExperienceSpell();
            case NinjaSpellFactory.SPELL_SMOKE_BOMB:
                return new SmokeBombSpell();
            case NinjaSpellFactory.SPELL_COUNTERSTRIKE:
                return new CounterstrikeSpell();
            case NinjaSpellFactory.SPELL_REVERSE_PAIN:
                return new ReversePainSpell();
            case NinjaSpellFactory.SPELL_DEATH_STRIKE:
                return new DeathStrikeSpell();
            default:
                return null;
        }
    }
}
