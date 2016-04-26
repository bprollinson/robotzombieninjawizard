package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.FullPotion;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.ViperDagger;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class Viper extends EnemyCharacter
{
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
          EnemyCharacter.STAT_ACCURACY
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Viper.mapCharacter, this);
    }

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new FullPotion(), 1);
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
        return new EquipmentGroup(new ViperDagger(), 1);
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Stung by the viper - you are poisoned");
        mainCharacter.getStatusEffects().poison();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyMeleeActionCalculator();
    }
}
