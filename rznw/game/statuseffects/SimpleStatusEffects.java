package rznw.game.statuseffects;

import java.util.HashMap;

public class SimpleStatusEffects
{
    public static final int NUM_STATUS_EFFECTS = 7;

    public static final int EFFECT_POISONED = 0;
    public static final int EFFECT_REVERSE_PAIN = 1;
    public static final int EFFECT_DEATH_STRIKE = 2;
    public static final int EFFECT_SMOKE_BOMB = 3;
    public static final int EFFECT_COUNTERSTRIKE = 4;
    public static final int EFFECT_INFER_ZOMBIE = 5;
    public static final int EFFECT_REGENERATE_SHOP = 6;

    private HashMap<Integer, Boolean> statusEffects = new HashMap<Integer, Boolean>();

    public SimpleStatusEffects()
    {
        for (int i = 0; i < SimpleStatusEffects.NUM_STATUS_EFFECTS; i++)
        {
            this.statusEffects.put(i, false);
        }

        this.statusEffects.put(SimpleStatusEffects.EFFECT_REGENERATE_SHOP, true);
    }

    public boolean getStatusEffect(int index)
    {
        return this.statusEffects.get(index);
    }

    public void setStatusEffect(int index, boolean value)
    {
        this.statusEffects.put(index, value);
    }
}
