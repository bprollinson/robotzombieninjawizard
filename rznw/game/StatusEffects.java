package rznw.game;

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

    private HashMap<Integer, Boolean> statusEffects = new HashMap<Integer, Boolean>();
    private HashMap<Integer, Integer> statusEffectTurns = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> otherStats = new HashMap<Integer, Integer>();

    public static final int NUM_STATUS_EFFECTS = 7;
    public static final int NUM_STATUS_EFFECT_TURNS = 16;
    public static final int NUM_STATS = 8;

    public static final int EFFECT_POISONED = 0;
    public static final int EFFECT_REVERSE_PAIN = 1;
    public static final int EFFECT_DEATH_STRIKE = 2;
    public static final int EFFECT_SMOKE_BOMB = 3;
    public static final int EFFECT_COUNTERSTRIKE = 4;
    public static final int EFFECT_INFER_ZOMBIE = 5;
    public static final int EFFECT_REGENERATE_SHOP = 6;

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
    private static final int EFFECT_POWER_SEARCH = 13;
    public static final int EFFECT_CONFUSION = 14;
    public static final int EFFECT_FROZEN = 15;

    private static final int STAT_ARMOR_BREAK = 0;
    private static final int STAT_DETECT_VITALITY_RADIUS = 1;
    private static final int STAT_ITEM_TRADE_NUMBER = 2;
    private static final int STAT_PRICE_REDUCTION_PERCENT = 3;
    private static final int STAT_BONUS_DROP_PROBABILITY = 4;
    private static final int STAT_BONUS_GOLD_PERCENT = 5;
    private static final int STAT_MEAT_SHIELD_PADDING_PERCENT = 6;
    private static final int STAT_MEAT_SHIELD_DODGE_PERCENT = 7;

    public StatusEffects(Character character)
    {
        this.character = character;

        this.statusEffects.put(StatusEffects.EFFECT_POISONED, false);
        this.statusEffects.put(StatusEffects.EFFECT_REVERSE_PAIN, false);
        this.statusEffects.put(StatusEffects.EFFECT_DEATH_STRIKE, false);
        this.statusEffects.put(StatusEffects.EFFECT_SMOKE_BOMB, false);
        this.statusEffects.put(StatusEffects.EFFECT_COUNTERSTRIKE, false);
        this.statusEffects.put(StatusEffects.EFFECT_INFER_ZOMBIE, false);
        this.statusEffects.put(StatusEffects.EFFECT_REGENERATE_SHOP, true);

        this.statusEffectTurns.put(StatusEffects.EFFECT_SIGNAL_WEAPON, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_THORN_SKIN, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_POISON_SKIN, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_BARBED_SKIN, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_RESIST_DAMAGE, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_INFECTIOUS_RAGE, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_FEED_BRAIN, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_SKIP, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_RAGE, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_MAGIC_SEEDS, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_MANA_SUCK, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_MEAT_SHIELD, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_INVISIBLE, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_POWER_SEARCH, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_CONFUSION, 0);
        this.statusEffectTurns.put(StatusEffects.EFFECT_FROZEN, 0);

        this.otherStats.put(StatusEffects.STAT_ARMOR_BREAK, 0);
        this.otherStats.put(StatusEffects.STAT_DETECT_VITALITY_RADIUS, 0);
        this.otherStats.put(StatusEffects.STAT_ITEM_TRADE_NUMBER, 0);
        this.otherStats.put(StatusEffects.STAT_PRICE_REDUCTION_PERCENT, 0);
        this.otherStats.put(StatusEffects.STAT_BONUS_DROP_PROBABILITY, 0);
        this.otherStats.put(StatusEffects.STAT_BONUS_GOLD_PERCENT, 0);
        this.otherStats.put(StatusEffects.STAT_MEAT_SHIELD_PADDING_PERCENT, 0);
        this.otherStats.put(StatusEffects.STAT_MEAT_SHIELD_DODGE_PERCENT, 0);
    }

    public boolean getStatusEffect(int index)
    {
        return this.statusEffects.get(index);
    }

    public void setStatusEffect(int index, boolean value)
    {
        this.statusEffects.put(index, value);
    }

    public int getStatusEffectTurns(int index)
    {
        return this.statusEffectTurns.get(index);
    }

    public void setStatusEffectTurns(int index, int value)
    {
        this.statusEffectTurns.put(index, value);
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
            this.statusEffectTurns.put(StatusEffects.EFFECT_FROZEN, 1);
        }
    }

    public void poison()
    {
        if (!this.thickSkinDodgesEffect())
        {
            this.statusEffects.put(StatusEffects.EFFECT_POISONED, true);
        }
    }

    public void confuse()
    {
        if (!this.thickSkinDodgesEffect())
        {
            this.statusEffectTurns.put(StatusEffects.EFFECT_CONFUSION, 3);
        }
    }

    public void breakArmor(int armorBreakPercent)
    {
        this.otherStats.put(StatusEffects.STAT_ARMOR_BREAK, armorBreakPercent);
    }

    public int getArmorBreakPercent()
    {
        return this.otherStats.get(StatusEffects.STAT_ARMOR_BREAK);
    }

    public void enableDetectVitality(int detectVitalityRadius)
    {
        this.otherStats.put(StatusEffects.STAT_DETECT_VITALITY_RADIUS, detectVitalityRadius);
    }

    public boolean detectVitalityEnabled()
    {
        return this.otherStats.get(StatusEffects.STAT_DETECT_VITALITY_RADIUS) > 0;
    }

    public void disableDetectVitality()
    {
        this.otherStats.put(StatusEffects.STAT_DETECT_VITALITY_RADIUS, 0);
    }

    public void enableItemTrade(int itemTradeNumber)
    {
        this.otherStats.put(StatusEffects.STAT_ITEM_TRADE_NUMBER, itemTradeNumber);
    }

    public boolean itemTradeEnabled()
    {
        return this.otherStats.get(StatusEffects.STAT_ITEM_TRADE_NUMBER) > 0;
    }

    public void disableItemTrade()
    {
        this.otherStats.put(StatusEffects.STAT_ITEM_TRADE_NUMBER, 0);
    }

    public void enableSummonShopkeeper(int priceReductionPercent)
    {
        this.otherStats.put(StatusEffects.STAT_PRICE_REDUCTION_PERCENT, priceReductionPercent);
    }

    public void disableSummonShopkeeper()
    {
        this.otherStats.put(StatusEffects.STAT_PRICE_REDUCTION_PERCENT, 0);
    }

    public boolean summonShopkeeperEnabled()
    {
        return this.otherStats.get(StatusEffects.STAT_PRICE_REDUCTION_PERCENT) > 0;
    }

    public void enableBoostGenetics(int bonusDropProbability, int bonusGoldPercent)
    {
        this.otherStats.put(StatusEffects.STAT_BONUS_DROP_PROBABILITY, bonusDropProbability);
        this.otherStats.put(StatusEffects.STAT_BONUS_GOLD_PERCENT, bonusGoldPercent);
    }

    public int getBonusDropProbability()
    {
        return this.otherStats.get(StatusEffects.STAT_BONUS_DROP_PROBABILITY);
    }

    public int getBonusGoldPercent()
    {
        return this.otherStats.get(StatusEffects.STAT_BONUS_GOLD_PERCENT);
    }

    public int getMeatShieldPaddingPercent()
    {
        return this.otherStats.get(StatusEffects.STAT_MEAT_SHIELD_PADDING_PERCENT);
    }

    public int getMeatShieldDodgePercent()
    {
        return this.otherStats.get(StatusEffects.STAT_MEAT_SHIELD_DODGE_PERCENT);
    }

    public void enableMeatShield(int numTurns, int bonusPadding, int bonusDodge)
    {
        this.statusEffectTurns.put(StatusEffects.EFFECT_MEAT_SHIELD, numTurns);
        this.otherStats.put(StatusEffects.STAT_MEAT_SHIELD_PADDING_PERCENT, bonusPadding);
        this.otherStats.put(StatusEffects.STAT_MEAT_SHIELD_DODGE_PERCENT, bonusDodge);
    }

    private boolean thickSkinDodgesEffect()
    {
        if (!this.character.isMainCharacter())
        {
            return false;
        }

        int statPoints = ((MainCharacter)this.character).getStatPoints(Stat.STAT_THICK_SKIN);
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

    public boolean isInvisible()
    {
        return this.statusEffectTurns.get(StatusEffects.EFFECT_INVISIBLE) > 0;
    }

    public int getInvisibilityTurns()
    {
        return this.statusEffectTurns.get(StatusEffects.EFFECT_INVISIBLE);
    }

    public void enablePowerSearch(int numTurns)
    {
        this.statusEffectTurns.put(StatusEffects.EFFECT_POWER_SEARCH, numTurns);
    }

    public boolean powerSearchEnabled()
    {
        return this.statusEffectTurns.get(StatusEffects.EFFECT_POWER_SEARCH) > 0;
    }

    public boolean regenerateShopEnabled()
    {
        return this.statusEffects.get(StatusEffects.EFFECT_REGENERATE_SHOP);
    }

    public void processTurn(Character character, GameWorld gameWorld)
    {
        if (this.statusEffects.get(StatusEffects.EFFECT_POISONED))
        {
            System.out.println("Damaging character due to poison");
            character.damage(StatusEffects.POISON_DAMAGE, null, gameWorld, Character.DAMAGE_SOURCE_OTHER);
        }

        this.statusEffects.put(StatusEffects.EFFECT_REVERSE_PAIN, false);

        Iterator<Map.Entry<Integer, Integer>> iterator = this.statusEffectTurns.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<Integer, Integer> pair = iterator.next();
            int turnsRemaining = pair.getValue();

            if (turnsRemaining > 0)
            {
                this.statusEffectTurns.put(pair.getKey(), turnsRemaining - 1);
            }
        }
    }
}
