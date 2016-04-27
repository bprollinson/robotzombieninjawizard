package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.LeechMail;
import rznw.game.maincharacter.inventory.ManaPotion;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;

public class Leech extends EnemyCharacter
{
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
          EnemyCharacter.STAT_HEALTH,
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_ACCURACY
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

    public EquipmentGroup getEquipmentDrops()
    {
        return new EquipmentGroup(new LeechMail(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyMeleeActionCalculator();
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Your are being leeched!");

        int mp = Math.max(mainCharacter.getMP() - 5, 0);
        mainCharacter.setMP(mp);
    }
}
