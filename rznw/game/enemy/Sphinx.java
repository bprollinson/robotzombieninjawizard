package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.ManaPotion;
import rznw.game.maincharacter.inventory.RiddleWand;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;

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

    public InventoryItem getItemDrop()
    {
        return new ManaPotion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new RiddleWand();
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
