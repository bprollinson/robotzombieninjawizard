package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.MeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.FullPotion;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.ViperDagger;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;
import rznw.ui.LogRendererFactory;

public class Viper extends EnemyCharacter
{
    public static final int ENEMY_NUMBER = 22;

    private static char mapCharacter = 'v';

    public Viper(int level)
    {
        super(level);
    }

    public Viper getNewInstance(int level)
    {
        return new Viper(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyStat.STAT_ACCURACY
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Viper.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new FullPotion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new ViperDagger();
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        boolean poisoned = mainCharacter.getStatusEffects().poison();

        if (poisoned)
        {
            LogRendererFactory.instance().log("You become poisoned.");
        }
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new MeleeActionCalculator();
    }

    public int getEnemyNumber()
    {
        return Viper.ENEMY_NUMBER;
    }

    public String getLogName()
    {
        return "viper";
    }
}
