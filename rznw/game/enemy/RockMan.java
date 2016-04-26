package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.maincharacter.inventory.RockMail;
import rznw.map.element.EnemyMapElement;

public class RockMan extends EnemyCharacter
{
    private static char mapCharacter = 'r';

    public RockMan(int level)
    {
        super(level);
    }

    public RockMan getNewInstance(int level)
    {
        return new RockMan(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_PADDING,
          EnemyCharacter.STAT_ACCURACY,
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, RockMan.mapCharacter, this);
    }

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new Potion(), 1);
    }

    public EquipmentGroup getEquipmentDrops()
    {
        return new EquipmentGroup(new RockMail(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyMeleeActionCalculator();
    }
}
