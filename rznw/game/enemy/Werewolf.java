package rznw.game.enemy;

import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Potion;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class Werewolf extends EnemyCharacter
{
    private static char mapCharacter = 'w';

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Werewolf.mapCharacter, this);
    }

    public boolean isDroppingItems()
    {
        return RandomNumberGenerator.rollSucceeds(50);
    }

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new Potion(), 1);
    }
}
