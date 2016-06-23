package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.MeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.maincharacter.inventory.ThiefGlove;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;

public class Thief extends EnemyCharacter
{
    public static final int ENEMY_NUMBER = 20;

    private static char mapCharacter = 't';

    public Thief(int level)
    {
        super(level);
    }

    public Thief getNewInstance(int level)
    {
        return new Thief(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_DODGE,
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_DODGE,
          EnemyCharacter.STAT_HEALTH
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Thief.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new Potion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new ThiefGlove();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new MeleeActionCalculator();
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("The thief is stealing your gold!");

        mainCharacter.getInventory().removeGold(10);
    }

    public int getEnemyNumber()
    {
        return Thief.ENEMY_NUMBER;
    }
}
