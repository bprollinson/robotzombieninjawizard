package rznw.game.enemy;

import rznw.game.Character;
import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.MeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.DragonPlate;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.ManaPotion;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;

public class Dragon extends EnemyCharacter
{
    public static final int ENEMY_NUMBER = 4;

    private static char mapCharacter = 'd';

    public Dragon(int level)
    {
        super(level);
    }

    public Dragon getNewInstance(int level)
    {
        return new Dragon(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyStat.STAT_HEALTH,
          EnemyStat.STAT_PADDING,
          EnemyStat.STAT_PADDING,
          EnemyStat.STAT_DAMAGE,
          EnemyStat.STAT_ACCURACY
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Dragon.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new ManaPotion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new DragonPlate();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new MeleeActionCalculator();
    }

    public void damagedByMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("The dragon retaliates by shooting fire");
        mainCharacter.damage(10, mainCharacter, gameWorld, Character.DAMAGE_SOURCE_OTHER);
    }

    public int getEnemyNumber()
    {
        return Dragon.ENEMY_NUMBER;
    }
}
