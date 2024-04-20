package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.MeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.ShieldOfSight;
import rznw.game.maincharacter.inventory.XRayDrop;
import rznw.map.element.EnemyMapElement;

public class XRayCat extends EnemyCharacter
{
    public static final int ENEMY_NUMBER = 24;

    private static char mapCharacter = 'x';

    public XRayCat(int level)
    {
        super(level);
    }

    public XRayCat getNewInstance(int level)
    {
        return new XRayCat(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyStat.STAT_DAMAGE,
          EnemyStat.STAT_ACCURACY,
          EnemyStat.STAT_DAMAGE,
          EnemyStat.STAT_ACCURACY,
          EnemyStat.STAT_PADDING
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, XRayCat.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new XRayDrop();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new ShieldOfSight();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new MeleeActionCalculator();
    }

    public int getViewRadius()
    {
        return 100;
    }

    public int getEnemyNumber()
    {
        return XRayCat.ENEMY_NUMBER;
    }

    public String getLogName()
    {
        return "x-ray cat";
    }
}
