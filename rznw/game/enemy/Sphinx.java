package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.MeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.ManaPotion;
import rznw.game.maincharacter.inventory.RiddleWand;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;
import rznw.ui.LogRendererFactory;

public class Sphinx extends EnemyCharacter
{
    public static final int ENEMY_NUMBER = 19;

    private static char mapCharacter = 's';

    public Sphinx(int level)
    {
        super(level);
    }

    public Sphinx getNewInstance(int level)
    {
        return new Sphinx(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyStat.STAT_HEALTH,
          EnemyStat.STAT_ACCURACY,
          EnemyStat.STAT_SIGHT
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Sphinx.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new ManaPotion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new RiddleWand();
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        mainCharacter.getStatusEffects().confuse();

        LogRendererFactory.instance().log("You become confused.");
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new MeleeActionCalculator();
    }

    public int getEnemyNumber()
    {
        return Sphinx.ENEMY_NUMBER;
    }

    public String getLogName()
    {
        return "sphinx";
    }
}
