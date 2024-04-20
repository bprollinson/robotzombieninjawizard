package rznw.game.statuseffects;

import rznw.game.Character;
import rznw.map.GameWorld;

public class StatusEffects
{
    private Character character;
    private SimpleStatusEffects simpleStatusEffects = new SimpleStatusEffects();
    private TurnBasedStatusEffects turnBasedStatusEffects = new TurnBasedStatusEffects();
    private StatusEffectStats statusEffectStats = new StatusEffectStats();

    public StatusEffects(Character character)
    {
        this.character = character;
    }

    public boolean getStatusEffect(int index)
    {
        return this.simpleStatusEffects.getStatusEffect(index);
    }

    public void setStatusEffect(int index, boolean value)
    {
        this.simpleStatusEffects.setStatusEffect(index, value);
    }

    public int getStatusEffectTurns(int index)
    {
        return this.turnBasedStatusEffects.getStatusEffectTurns(index);
    }

    public void setStatusEffectTurns(int index, int value)
    {
        this.turnBasedStatusEffects.setStatusEffectTurns(index, value);
    }

    public TurnBasedStatusEffects getTurnBasedStatusEffects()
    {
        return this.turnBasedStatusEffects;
    }

    public int getStat(int index)
    {
        return this.statusEffectStats.getStat(index);
    }

    public void setStat(int index, int value)
    {
        this.statusEffectStats.setStat(index, value);
    }

    public boolean freeze()
    {
        if (!this.thickSkinDodgesEffect())
        {
            this.setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_FROZEN, 1);
            return true;
        }

        return false;
    }

    public boolean poison()
    {
        if (!this.thickSkinDodgesEffect())
        {
            this.setStatusEffect(SimpleStatusEffects.EFFECT_POISONED, true);
            return true;
        }

        return false;
    }

    public boolean confuse()
    {
        if (!this.thickSkinDodgesEffect())
        {
            this.setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_CONFUSION, 3);
            return true;
        }

        return false;
    }

    public void enableBoostGenetics(int bonusDropProbability, int bonusGoldPercent)
    {
        this.setStat(StatusEffectStats.STAT_BONUS_DROP_PROBABILITY, bonusDropProbability);
        this.setStat(StatusEffectStats.STAT_BONUS_GOLD_PERCENT, bonusGoldPercent);
    }

    public void enableMeatShield(int numTurns, int bonusPadding, int bonusDodge)
    {
        this.setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_MEAT_SHIELD, numTurns);
        this.setStat(StatusEffectStats.STAT_MEAT_SHIELD_PADDING_PERCENT, bonusPadding);
        this.setStat(StatusEffectStats.STAT_MEAT_SHIELD_DODGE_PERCENT, bonusDodge);
    }

    private boolean thickSkinDodgesEffect()
    {
        return new StatusEffectDodgeCalculator().characterDodgesEffect(this.character);
    }

    public void processTurn(GameWorld gameWorld)
    {
        new StatusEffectsTurnProcessor().processTurn(gameWorld, this.character);
    }
}
