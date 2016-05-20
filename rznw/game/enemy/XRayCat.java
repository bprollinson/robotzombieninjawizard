package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.ShieldOfSight;
import rznw.game.maincharacter.inventory.XRayDrop;
import rznw.map.element.EnemyMapElement;

public class XRayCat extends EnemyCharacter
{
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
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_PADDING
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
        return new EnemyMeleeActionCalculator();
    }

    public int getViewRadius()
    {
        return 100;
    }
}
