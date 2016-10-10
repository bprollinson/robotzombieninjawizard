package rznw.game.statuseffects;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Armor;
import rznw.game.stat.Stat;
import rznw.map.GameWorld;
import rznw.utility.RandomNumberGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class StatusEffects
{
    private static final int POISON_DAMAGE = 10;

    private Character character;

    private SimpleStatusEffects simpleStatusEffects = new SimpleStatusEffects();
    private TurnBasedStatusEffects turnBasedStatusEffects = new TurnBasedStatusEffects();
    private HashMap<Integer, Integer> otherStats = new HashMap<Integer, Integer>();

    public StatusEffects(Character character)
    {
        this.character = character;

        for (int i = 0; i < StatusEffectStats.NUM_STATS; i++)
        {
            this.otherStats.put(i, 0);
        }
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

    public int getStat(int index)
    {
        return this.otherStats.get(index);
    }

    public void setStat(int index, int value)
    {
        this.otherStats.put(index, value);
    }

    public void freeze()
    {
        if (!this.thickSkinDodgesEffect())
        {
            this.setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_FROZEN, 1);
        }
    }

    public void poison()
    {
        if (!this.thickSkinDodgesEffect())
        {
            this.setStatusEffect(SimpleStatusEffects.EFFECT_POISONED, true);
        }
    }

    public void confuse()
    {
        if (!this.thickSkinDodgesEffect())
        {
            this.setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_CONFUSION, 3);
        }
    }

    public void enableBoostGenetics(int bonusDropProbability, int bonusGoldPercent)
    {
        this.otherStats.put(StatusEffectStats.STAT_BONUS_DROP_PROBABILITY, bonusDropProbability);
        this.otherStats.put(StatusEffectStats.STAT_BONUS_GOLD_PERCENT, bonusGoldPercent);
    }

    public void enableMeatShield(int numTurns, int bonusPadding, int bonusDodge)
    {
        this.setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_MEAT_SHIELD, numTurns);
        this.otherStats.put(StatusEffectStats.STAT_MEAT_SHIELD_PADDING_PERCENT, bonusPadding);
        this.otherStats.put(StatusEffectStats.STAT_MEAT_SHIELD_DODGE_PERCENT, bonusDodge);
    }

    private boolean thickSkinDodgesEffect()
    {
        if (!this.character.isMainCharacter())
        {
            return false;
        }

        int statPoints = ((MainCharacter)this.character).getStats().getStatPoints(Stat.STAT_THICK_SKIN);
        int probability = 5 * statPoints;

        Armor armor = ((MainCharacter)character).getEquipment().getEquippedArmor();
        if (armor != null)
        {
            System.out.println("Adding additional thick skin stats from armor");
            probability += armor.getThickSkinBonus();
        }

        boolean success = RandomNumberGenerator.rollSucceeds(probability);

        if (success)
        {
            System.out.println("Dodging status effect due to thick skin");
        }

        return success;
    }

    public void processTurn(Character character, GameWorld gameWorld)
    {
        if (this.getStatusEffect(SimpleStatusEffects.EFFECT_POISONED))
        {
            System.out.println("Damaging character due to poison");
            character.damage(StatusEffects.POISON_DAMAGE, null, gameWorld, Character.DAMAGE_SOURCE_OTHER);
        }

        this.setStatusEffect(SimpleStatusEffects.EFFECT_REVERSE_PAIN, false);

        Iterator<Map.Entry<Integer, Integer>> iterator = this.turnBasedStatusEffects.getEntrySetIterator();
        while (iterator.hasNext())
        {
            Map.Entry<Integer, Integer> pair = iterator.next();
            int turnsRemaining = pair.getValue();

            if (turnsRemaining > 0)
            {
                this.setStatusEffectTurns(pair.getKey(), turnsRemaining - 1);
            }
        }
    }
}
