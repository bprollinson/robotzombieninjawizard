package rznw.game.enemy;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.Zombie;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.map.GameWorld;
import rznw.map.ShortestPathCalculator;
import rznw.map.element.MapElement;
import rznw.map.generator.MapPoint;
import rznw.map.generator.direction.PathDirection;
import rznw.map.generator.path.MapPath;
import rznw.map.generator.path.MapPathCache;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;
import rznw.utility.RandomNumberGenerator;

public abstract class EnemyCharacter extends Character
{
    protected static int STAT_ACCURACY = 1;
    protected static int STAT_SIGHT = 3;
    protected static int STAT_DAMAGE = 4;
    protected static int STAT_PADDING = 5;

    private int level;

    /**
     * Health
     * Accuracy
     * Dodge
     * Sight
     * Damage
     * Padding
     * Mana
     * Mana Burn
     */
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

    public EnemyAIBasedPositionChange getPositionChange(GameWorld gameWorld)
    {
        if (this.getStatusEffects().isConfused() || this.distanceFromMainCharacter(gameWorld) > this.getViewRadius())
        {
            if (this.getStatusEffects().isConfused())
            {
                System.out.println("Enemy is confused!");
            }

            return this.getRandomPositionChange();
        }

        MainCharacter character = gameWorld.getMainCharacter();

        MapPoint startPoint = new MapPoint(this.getMapElement().getColumn(), this.getMapElement().getRow());
        MapPoint endPoint = new MapPoint(character.getMapElement().getColumn(), character.getMapElement().getRow());
        ShortestPathCalculator pathCalculator = new ShortestPathCalculator(gameWorld.getMap(), false, true);
        MapPath path = pathCalculator.calculateShortestPath(startPoint, endPoint);

        System.out.println("Path: " + path);

        PathDirection firstDirection = path.getDirection(0);

        System.out.println("Direction: " + firstDirection.getDeltaY() + ", " + firstDirection.getDeltaX());

        return new EnemyAIBasedPositionChange(this, firstDirection.getDeltaY(), firstDirection.getDeltaX());
    }

    public EnemyAIBasedPositionChange getRandomPositionChange()
    {
        EnemyAIBasedPositionChange possibleChanges[] = {
           new EnemyAIBasedPositionChange(this, -1, -1),
           new EnemyAIBasedPositionChange(this, -1, 0),
           new EnemyAIBasedPositionChange(this, -1, 1),
           new EnemyAIBasedPositionChange(this, 0, -1),
           new EnemyAIBasedPositionChange(this, 0, 1),
           new EnemyAIBasedPositionChange(this, 1, -1),
           new EnemyAIBasedPositionChange(this, 1, 0),
           new EnemyAIBasedPositionChange(this, 1, 1)
        };

        int randomPosition = RandomNumberGenerator.randomInteger(0, possibleChanges.length - 1);
        return possibleChanges[randomPosition];
    }

    public int getMaxHP()
    {
        return 100;
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
        return false;
    }

    public void damage(int damage, Character damageSource, GameWorld gameWorld, int damageSourceType)
    {
        int paddingPercent = 2 * this.getStatPoints(EnemyCharacter.STAT_PADDING);

        int padding = (int)Math.floor(paddingPercent / 100.0 * damage);

        if (padding > 0)
        {
            System.out.println("Enemy is preventing " + padding + " damage via padding");
        }

        damage -= padding;

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

    private double distanceFromMainCharacter(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        MapElement element = this.getMapElement();
        MapElement characterElement = character.getMapElement();

        return Math.sqrt(Math.pow(element.getRow() - characterElement.getRow(), 2) + Math.pow(element.getColumn() - characterElement.getColumn(), 2));
    }

    private int getViewRadius()
    {
        return 5 + this.getStatPoints(EnemyCharacter.STAT_SIGHT);
    }

    protected abstract int[] getStatSequence();
}
