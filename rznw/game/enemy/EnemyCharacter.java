package rznw.game.enemy;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.Zombie;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.map.GameWorld;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;
import rznw.utility.RandomNumberGenerator;

public abstract class EnemyCharacter extends Character
{
    public EnemyAIBasedPositionChange getPositionChange(MainCharacter character)
    {
        if (this.getStatusEffects().isConfused())
        {
            System.out.println("Enemy is confused!");

            return this.getRandomPositionChange();
        }

        int enemyRow = this.getMapElement().getRow();
        int enemyColumn = this.getMapElement().getColumn();

        int characterRow = character.getMapElement().getRow();
        int characterColumn = character.getMapElement().getColumn();

        int deltaRow = 0;
        if (enemyRow < characterRow)
        {
            deltaRow = 1;
        }
        if (enemyRow > characterRow)
        {
            deltaRow = -1;
        }

        int deltaColumn = 0;
        if (enemyColumn < characterColumn)
        {
            deltaColumn = 1;
        }
        if (enemyColumn > characterColumn)
        {
            deltaColumn = -1;
        }

        return new EnemyAIBasedPositionChange(this, deltaRow, deltaColumn);
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

    public void damage(int damage, Character damageSource, GameWorld gameWorld)
    {
        if (damageSource instanceof Zombie && damageSource.getStatusEffects().infectiousRageEnabled())
        {
            System.out.println("Infectious rage is enabled");
            System.out.println("Base damage: " + damage);
            damage = (int)Math.floor(1.5 * damage);
            System.out.println("Rage damage: " + damage);
        }

        super.damage(damage, damageSource, gameWorld);
    }
}
