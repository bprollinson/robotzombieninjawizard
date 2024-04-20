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
import rznw.ui.LogRendererFactory;

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
          EnemyStat.STAT_ACCURACY,
          EnemyStat.STAT_DODGE,
          EnemyStat.STAT_ACCURACY,
          EnemyStat.STAT_DODGE,
          EnemyStat.STAT_HEALTH
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
        mainCharacter.getInventory().removeGold(10);

        LogRendererFactory.instance().log("Enemy steals 10 gold.");
    }

    public int getEnemyNumber()
    {
        return Thief.ENEMY_NUMBER;
    }

    public String getLogName()
    {
        return "thief";
    }
}
