package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.MeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.EtherealShield;
import rznw.game.maincharacter.inventory.FullManaPotion;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.map.element.EnemyMapElement;

public class Phantasm extends EnemyCharacter
{
    public static final int ENEMY_NUMBER = 16;

    private static char mapCharacter = 'p';

    public Phantasm(int level)
    {
        super(level);
    }

    public Phantasm getNewInstance(int level)
    {
        return new Phantasm(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_ACCURACY
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Phantasm.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new FullManaPotion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new EtherealShield();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new MeleeActionCalculator();
    }

    public boolean dodgesAttack()
    {
        System.out.println("The phantasm will always dodge your attack!");

        return true;
    }

    public int getEnemyNumber()
    {
        return Phantasm.ENEMY_NUMBER;
    }
}
