package rznw.game.enemy;

import rznw.game.Character;
import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.calculator.EnemyCharacterDamageDealtCalculator;
import rznw.game.enemy.calculator.EnemyCharacterDamageReceivedCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public abstract class EnemyCharacter extends Character
{
    public static int STAT_HEALTH = 0;
    public static int STAT_ACCURACY = 1;
    public static int STAT_DODGE = 2;
    public static int STAT_SIGHT = 3;
    public static int STAT_DAMAGE = 4;
    public static int STAT_PADDING = 5;
    public static int STAT_MANA = 6;
    public static int STAT_MANA_BURN = 7;

    protected int level;

    private int[] stats;

    public EnemyCharacter(int level)
    {
        super();

        this.level = level;

        this.stats = new int[8];

        for (int i = 0; i < this.stats.length; i++)
        {
            this.stats[i] = 0;
        }

        this.applyStatSequence();

        this.HP = this.getMaxHP();
        this.MP = this.getMaxMP();
    }

    public abstract EnemyCharacter getNewInstance(int level);

    public int getLevel()
    {
        return this.level;
    }

    public abstract EnemyActionCalculator getActionCalculator(); 

    public EnemyAction getAction(GameWorld gameWorld)
    {
        return this.getActionCalculator().getAction(gameWorld, this);
    }

    public int getMaxHP()
    {
        int result = 200;

        if (this.stats != null)
        {
            result += 20 * this.getStatPoints(EnemyCharacter.STAT_HEALTH);
            System.out.println("Enemy max HP: " + result);
        }

        return result;
    }

    public int getMaxMP()
    {
        int result = 200;

        if (this.stats != null)
        {
            result += 20 * this.getStatPoints(EnemyCharacter.STAT_MANA);
            System.out.println("Enemy max MP: " + result);
        }

        return result;
    }

    public int getDamage()
    {
        return new EnemyCharacterDamageDealtCalculator().getDamage(this);
    }

    public int getNumGold()
    {
        return 20 + 5 * this.level;
    }

    public boolean isDroppingItems(MainCharacter mainCharacter)
    {
        int probability = 50 + 2 * mainCharacter.getSkillPoints(MainCharacter.SKILL_FAST_HANDS);
        probability += this.getStatusEffects().getBonusDropProbability();
        System.out.println("Item drop probability: " + probability);

        return RandomNumberGenerator.rollSucceeds(probability);
    }

    public abstract InventoryItem getItemDrop();

    public final boolean isDroppingEquipment()
    {
        int probability = 10;
        probability += this.getStatusEffects().getBonusDropProbability();
        System.out.println("Equipment drop probability: " + probability);

        return RandomNumberGenerator.rollSucceeds(probability);
    }

    public abstract EquipmentItem getEquipmentDrop();

    public int getExperienceReward()
    {
        return 20 + 5 * this.level;
    }

    public boolean meleeAttackHits()
    {
        int toHitPercent = 50 + 2 * this.getStatPoints(EnemyCharacter.STAT_ACCURACY);
        System.out.println("Enemy melee percent: " + toHitPercent);

        return RandomNumberGenerator.rollSucceeds(toHitPercent);
    }

    public boolean dodgesAttack()
    {
        int toDodgePercent = 2 *  this.getStatPoints(EnemyCharacter.STAT_DODGE);
        System.out.println("Enemy chance to dodge: " + toDodgePercent);

        return RandomNumberGenerator.rollSucceeds(toDodgePercent);
    }

    public int damage(int damage, Character damageSource, GameWorld gameWorld, int damageSourceType)
    {
        damage = new EnemyCharacterDamageReceivedCalculator().getDamage(this, damage, damageSource, damageSourceType);

        return super.damage(damage, damageSource, gameWorld, damageSourceType);
    }

    public int getStatPoints(int statNumber)
    {
        return this.stats[statNumber];
    }

    private void applyStatSequence()
    {
        int desiredSequenceLength = 4 * this.level;

        int[] statSequence = this.getStatSequence();

        for (int i = 0; i < desiredSequenceLength; i++)
        {
            int statIndex = statSequence[i % statSequence.length];
            this.stats[statIndex]++;
        }
    }

    public double distanceFromMainCharacter(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        MapElement element = this.getMapElement();
        MapElement characterElement = character.getMapElement();

        return Math.sqrt(Math.pow(element.getRow() - characterElement.getRow(), 2) + Math.pow(element.getColumn() - characterElement.getColumn(), 2));
    }

    public int getViewRadius()
    {
        return 5 + this.getStatPoints(EnemyCharacter.STAT_SIGHT);
    }

    protected abstract int[] getStatSequence();

    public boolean isEnemy()
    {
        return true;
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
    }

    public void damagedByMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
    }

    public abstract int getEnemyNumber();
}
