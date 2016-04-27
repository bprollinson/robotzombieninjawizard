package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.AssassinsCloak;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.Potion;
import rznw.map.element.EnemyMapElement;

public class Assassin extends EnemyCharacter
{
    private static char mapCharacter = 'a';

    public Assassin(int level)
    {
        super(level);
    }

    public Assassin getNewInstance(int level)
    {
        return new Assassin(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_DODGE,
          EnemyCharacter.STAT_HEALTH
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Assassin.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new Potion();
    }

    public EquipmentGroup getEquipmentDrops()
    {
        return new EquipmentGroup(new AssassinsCloak(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyMeleeActionCalculator();
    }
}
