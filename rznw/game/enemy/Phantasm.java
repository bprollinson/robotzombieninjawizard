package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EtherealShield;
import rznw.game.maincharacter.inventory.FullManaPotion;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class Phantasm extends EnemyCharacter
{
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

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new FullManaPotion(), 1);
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
        return new EquipmentGroup(new EtherealShield(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyMeleeActionCalculator();
    }

    public boolean dodgesAttack()
    {
        System.out.println("The phantasm will always dodge your attack!");

        return true;
    }
}
