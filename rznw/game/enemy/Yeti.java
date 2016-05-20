package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.IceRod;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.SanityDrop;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class Yeti extends EnemyCharacter
{
    private static char mapCharacter = 'y';

    public Yeti(int level)
    {
        super(level);
    }

    public Yeti getNewInstance(int level)
    {
        return new Yeti(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_ACCURACY,
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Yeti.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new SanityDrop();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new IceRod();
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Struck by the yeti");

        if (RandomNumberGenerator.rollSucceeds(50))
        {
            System.out.println("You are frozen");
            mainCharacter.getStatusEffects().freeze();
        }
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyMeleeActionCalculator();
    }
}
