package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.MeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.AssassinsCloak;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.Potion;
import rznw.map.element.EnemyMapElement;

public class Assassin extends EnemyCharacter
{
    private static final int ENEMY_NUMBER = 1;

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

    public EquipmentItem getEquipmentDrop()
    {
        return new AssassinsCloak();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new MeleeActionCalculator();
    }

    public int getEnemyNumber()
    {
        return Assassin.ENEMY_NUMBER;
    }
}
