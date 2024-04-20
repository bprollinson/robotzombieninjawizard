package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.MeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.IceRod;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.SanityDrop;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;
import rznw.ui.LogRendererFactory;

public class Yeti extends EnemyCharacter
{
    public static final int ENEMY_NUMBER = 25;

    private static char mapCharacter = 'y';

    public Yeti(int level)
    {
        super(level);
    }

    public Yeti getNewInstance(int level)
    {
        return new Yeti(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyStat.STAT_DAMAGE,
          EnemyStat.STAT_DAMAGE,
          EnemyStat.STAT_ACCURACY,
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Yeti.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new SanityDrop();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new IceRod();
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        if (RandomNumberGenerator.rollSucceeds(50))
        {
            boolean frozen = mainCharacter.getStatusEffects().freeze();

            if (frozen)
            {
                LogRendererFactory.instance().log("You become frozen.");
            }
        }
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new MeleeActionCalculator();
    }

    public int getEnemyNumber()
    {
        return Yeti.ENEMY_NUMBER;
    }

    public String getLogName()
    {
        return "yeti";
    }
}
