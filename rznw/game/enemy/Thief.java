package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.maincharacter.inventory.ThiefGlove;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class Thief extends EnemyCharacter
{
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
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_DODGE,
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_DODGE,
          EnemyCharacter.STAT_HEALTH
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Thief.mapCharacter, this);
    }

    public boolean isDroppingItems(MainCharacter mainCharacter)
    {
        int probability = 50 + 2 * mainCharacter.getSkillPoints(6);
        probability += this.getStatusEffects().getBonusDropProbability();
        System.out.println("Item drop probability: " + probability);

        return RandomNumberGenerator.rollSucceeds(probability);
    }

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new Potion(), 1);
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
        return new EquipmentGroup(new ThiefGlove(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyMeleeActionCalculator();
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage)
    {
        System.out.println("The thief is stealing your gold!");

        mainCharacter.getInventory().removeGold(10);
    }
}
