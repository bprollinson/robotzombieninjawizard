package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.MeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Herb;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.WoodenSword;
import rznw.map.element.EnemyMapElement;

public class Mummy extends EnemyCharacter
{
    public static final int ENEMY_NUMBER = 13;

    private static char mapCharacter = 'm';

    public Mummy(int level)
    {
        super(level);
    }

    public Mummy getNewInstance(int level)
    {
        return new Mummy(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyStat.STAT_SIGHT,
          EnemyStat.STAT_PADDING
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Mummy.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new Herb();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new WoodenSword();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new MeleeActionCalculator();
    }

    public int getEnemyNumber()
    {
        return Mummy.ENEMY_NUMBER;
    }

    public String getLogName()
    {
        return "mummy";
    }
}
