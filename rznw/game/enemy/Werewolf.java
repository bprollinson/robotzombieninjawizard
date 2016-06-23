package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.MeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.maincharacter.inventory.WoodenShield;
import rznw.map.element.EnemyMapElement;

public class Werewolf extends EnemyCharacter
{
    public static final int ENEMY_NUMBER = 23;

    private static char mapCharacter = 'w';

    public Werewolf(int level)
    {
        super(level);
    }

    public Werewolf getNewInstance(int level)
    {
        return new Werewolf(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_ACCURACY
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Werewolf.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new Potion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new WoodenShield();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new MeleeActionCalculator();
    }

    public int getEnemyNumber()
    {
        return Werewolf.ENEMY_NUMBER;
    }
}
