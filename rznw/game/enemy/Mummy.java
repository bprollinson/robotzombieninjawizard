package rznw.game.enemy;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Herb;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class Mummy extends EnemyCharacter
{
    private static char mapCharacter = 'm';

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Mummy.mapCharacter, this);
    }

    public boolean isDroppingItems(MainCharacter mainCharacter)
    {
        int probability = 50 + 2 * mainCharacter.getSkillPoints(6);
        System.out.println("Item drop probability: " + probability);

        return RandomNumberGenerator.rollSucceeds(probability);
    }

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new Herb(), 1);
    }
}
