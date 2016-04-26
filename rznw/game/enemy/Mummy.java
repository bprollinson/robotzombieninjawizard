package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Herb;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
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

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new Herb(), 1);
    }

    public EquipmentGroup getEquipmentDrops()
    {
        return new EquipmentGroup(new WoodenSword(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyMeleeActionCalculator();
    }
}
