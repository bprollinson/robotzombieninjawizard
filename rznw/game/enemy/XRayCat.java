package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.ShieldOfSight;
import rznw.game.maincharacter.inventory.XRayDrop;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

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

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new XRayDrop(), 1);
    }

    public boolean isDroppingEquipment()
    {
        int probability = 10;
        probability += this.getStatusEffects().getBonusDropProbability();
        System.out.println("Equipment drop probability: " + probability);

        return RandomNumberGenerator.rollSucceeds(probability);
    }

    public EquipmentGroup getEquipmentDrops()
    {
        return new EquipmentGroup(new ShieldOfSight(), 1);
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
