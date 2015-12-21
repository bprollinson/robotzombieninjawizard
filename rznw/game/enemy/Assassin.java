package rznw.game.enemy;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.AssassinsCloak;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Potion;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class Assassin extends EnemyCharacter
{
    private static char mapCharacter = 'a';

    public Assassin(int level)
    {
        super(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_DODGE,
          EnemyCharacter.STAT_HEALTH
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Assassin.mapCharacter, this);
    }

    public boolean isDroppingItems(MainCharacter mainCharacter)
    {
        int probability = 50 + 2 * mainCharacter.getSkillPoints(6);
        System.out.println("Item drop probability: " + probability);

        return RandomNumberGenerator.rollSucceeds(probability);
    }

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new Potion(), 1);
    }

    public boolean isDroppingEquipment()
    {
        return RandomNumberGenerator.rollSucceeds(10);
    }

    public EquipmentGroup getEquipmentDrops()
    {
        return new EquipmentGroup(new AssassinsCloak(), 1);
    }
}