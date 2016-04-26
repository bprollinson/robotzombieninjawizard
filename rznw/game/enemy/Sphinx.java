package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.ManaPotion;
import rznw.game.maincharacter.inventory.RiddleWand;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class Sphinx extends EnemyCharacter
{
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
          EnemyCharacter.STAT_HEALTH,
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_SIGHT
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Sphinx.mapCharacter, this);
    }

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new ManaPotion(), 1);
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
        return new EquipmentGroup(new RiddleWand(), 1);
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Given a riddle by the Sphinx - you are confused");
        mainCharacter.getStatusEffects().confuse();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyMeleeActionCalculator();
    }
}
