package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Herb;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.WoodenSword;
import rznw.map.element.EnemyMapElement;

public class Mummy extends EnemyCharacter
{
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
          EnemyCharacter.STAT_SIGHT,
          EnemyCharacter.STAT_PADDING
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
        return new EnemyMeleeActionCalculator();
    }
}
