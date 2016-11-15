package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.MeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.LeechMail;
import rznw.game.maincharacter.inventory.ManaPotion;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;

public class Leech extends EnemyCharacter
{
    public static final int ENEMY_NUMBER = 12;

    private static char mapCharacter = 'l';

    public Leech(int level)
    {
        super(level);
    }

    public Leech getNewInstance(int level)
    {
        return new Leech(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyStat.STAT_HEALTH,
          EnemyStat.STAT_DAMAGE,
          EnemyStat.STAT_ACCURACY,
          EnemyStat.STAT_DAMAGE,
          EnemyStat.STAT_ACCURACY
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Leech.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new ManaPotion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new LeechMail();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new MeleeActionCalculator();
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Your are being leeched!");

        int mp = Math.max(mainCharacter.getMP() - 5, 0);
        mainCharacter.setMP(mp);
    }

    public int getEnemyNumber()
    {
        return Leech.ENEMY_NUMBER;
    }

    public String getLogName()
    {
        return "leech";
    }
}
