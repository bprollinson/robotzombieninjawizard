package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.maincharacter.inventory.ThiefGlove;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;

public class Thief extends EnemyCharacter
{
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

    public EquipmentGroup getEquipmentDrops()
    {
        return new EquipmentGroup(new ThiefGlove(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyMeleeActionCalculator();
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("The thief is stealing your gold!");

        mainCharacter.getInventory().removeGold(10);
    }
}
