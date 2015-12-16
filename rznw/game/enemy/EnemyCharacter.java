package rznw.game.enemy;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.Zombie;
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
    private static int SIGHT_RADIUS = 10;

    public EnemyAIBasedPositionChange getPositionChange(GameWorld gameWorld)
    {
        if (this.getStatusEffects().isConfused() || this.distanceFromMainCharacter(gameWorld) > EnemyCharacter.SIGHT_RADIUS)
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
        return 10;
    }

    public int getNumGold()
    {
        return 20;
    }

    public abstract InventoryItemGroup getItemDrops();

    public int getExperienceReward()
    {
        return 20;
    }

    public boolean meleeAttackHits()
    {
        return true;
    }

    public boolean dodgesAttack()
    {
        return false;
    }

    public void damage(int damage, Character damageSource, GameWorld gameWorld, int damageSourceType)
    {
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

    private double distanceFromMainCharacter(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        MapElement element = this.getMapElement();
        MapElement characterElement = character.getMapElement();

        return Math.sqrt(Math.pow(element.getRow() - characterElement.getRow(), 2) + Math.pow(element.getColumn() - characterElement.getColumn(), 2));
    }
}
