package rznw.game.statuseffects;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TurnBasedStatusEffects
{
    public static final int NUM_STATUS_EFFECTS = 16;

    public static final int EFFECT_SIGNAL_WEAPON = 0;
    public static final int EFFECT_THORN_SKIN = 1;
    public static final int EFFECT_POISON_SKIN = 2;
    public static final int EFFECT_BARBED_SKIN = 3;
    public static final int EFFECT_RESIST_DAMAGE = 4;
    public static final int EFFECT_INFECTIOUS_RAGE = 5;
    public static final int EFFECT_FEED_BRAIN = 6;
    public static final int EFFECT_SKIP = 7;
    public static final int EFFECT_RAGE = 8;
    public static final int EFFECT_MAGIC_SEEDS = 9;
    public static final int EFFECT_MANA_SUCK = 10;
    public static final int EFFECT_MEAT_SHIELD = 11;
    public static final int EFFECT_INVISIBLE = 12;
    public static final int EFFECT_POWER_SEARCH = 13;
    public static final int EFFECT_CONFUSION = 14;
    public static final int EFFECT_FROZEN = 15;

    private HashMap<Integer, Integer> statusEffectTurns = new HashMap<Integer, Integer>();

    public TurnBasedStatusEffects()
    {
        for (int i = 0; i < TurnBasedStatusEffects.NUM_STATUS_EFFECTS; i++)
        {
            this.statusEffectTurns.put(i, 0);
        }
    }

    public int getStatusEffectTurns(int index)
    {
        return this.statusEffectTurns.get(index);
    }

    public void setStatusEffectTurns(int index, int value)
    {
        this.statusEffectTurns.put(index, value);
    }

    public Iterator<Map.Entry<Integer, Integer>> getEntrySetIterator()
    {
        return this.statusEffectTurns.entrySet().iterator();
    }
}
