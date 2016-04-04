package rznw.game.enemy;

import rznw.game.Character;
import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.Zombie;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public abstract class EnemyCharacter extends Character
{
    protected static int STAT_HEALTH = 0;
    protected static int STAT_ACCURACY = 1;
    protected static int STAT_DODGE = 2;
    protected static int STAT_SIGHT = 3;
    protected static int STAT_DAMAGE = 4;
    protected static int STAT_PADDING = 5;
    protected static int STAT_MANA = 6;
    protected static int STAT_MANA_BURN = 7;

    private int level;

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
        int result = 100;

        if (this.stats != null)
        {
            result += 10 * this.getStatPoints(EnemyCharacter.STAT_HEALTH);
            System.out.println("Enemy max HP: " + result);
        }

        return result;
    }

    public int getMaxMP()
    {
        return 100;
    }

    public int getDamage()
    {
        System.out.println("Enemy damage: " + (10 + 2 * this.getStatPoints(EnemyCharacter.STAT_DAMAGE)));
        return 10 + 2 * this.getStatPoints(EnemyCharacter.STAT_DAMAGE);
    }

    public int getNumGold()
    {
        return 20;
    }

    public abstract boolean isDroppingItems(MainCharacter mainCharacter);

    public abstract InventoryItemGroup getItemDrops();

    public abstract boolean isDroppingEquipment();

    public abstract EquipmentGroup getEquipmentDrops();

    public int getExperienceReward()
    {
        return 20;
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

    public void damage(int damage, Character damageSource, GameWorld gameWorld, int damageSourceType)
    {
        int paddingPercent = 2 * this.getStatPoints(EnemyCharacter.STAT_PADDING);

        if (this.getStatusEffects().getArmorBreakPercent() > 0)
        {
            int armorBreakPercent = this.getStatusEffects().getArmorBreakPercent();
            paddingPercent -= armorBreakPercent;

            System.out.println("Enemy armor break percent: " + armorBreakPercent);
        }

        int padding = (int)Math.floor(paddingPercent / 100.0 * damage);

        if (padding != 0)
        {
            System.out.println("Enemy is preventing " + padding + " of " + damage + " damage via padding");
        }

        damage -= padding;

        if (damageSource instanceof MainCharacter && damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            int bonusDamagePercent = 5 * ((MainCharacter)damageSource).getStatPoints(14);

            if (bonusDamagePercent > 0)
            {
                 int bonusDamage = (int)Math.floor(bonusDamagePercent / 100.0 * damage);
                 damage += bonusDamage;

                 System.out.println("Mana burn bonus damage: " + bonusDamage);
            }
        }

        if (damageSource instanceof Zombie && damageSource.getStatusEffects().infectiousRageEnabled())
        {
            System.out.println("Infectious rage is enabled");
            System.out.println("Base damage: " + damage);
            damage = (int)Math.floor(1.5 * damage);
            System.out.println("Rage damage: " + damage);
        }

        if (damageSource instanceof Zombie && damageSource.getStatusEffects().feedBrainEnabled() && damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            System.out.println("Feed brain is enabled");
            System.out.println("Base damage: " + damage);
            damage = (int)Math.floor(1.6 * damage);
            System.out.println("Feed brain damage: " + damage);
        }

        super.damage(damage, damageSource, gameWorld, damageSourceType);
    }

    private int getStatPoints(int statNumber)
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

    public void damagedMainCharacter(MainCharacter mainCharacter)
    {
    }
}
