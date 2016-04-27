package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Bomb;
import rznw.game.maincharacter.inventory.CrusherHammer;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;

public class Crusher extends EnemyCharacter
{
    private static char mapCharacter = 'c';

    private int bonusDamagePercent = 0;

    public Crusher(int level)
    {
        super(level);
    }

    public Crusher getNewInstance(int level)
    {
        return new Crusher(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_ACCURACY
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Crusher.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new Bomb();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new CrusherHammer();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyMeleeActionCalculator();
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Damaged the main character");

        this.enhanceRage();
    }

    public void damagedByMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Damaged by the main character");

        this.enhanceRage();
    }

    public int getDamage()
    {
        int damage = (int)Math.floor((1 + bonusDamagePercent / 100.0) * super.getDamage());

        System.out.println(super.getDamage() + " is promoted to " + damage);

        return damage;
    }

    private void enhanceRage()
    {
        this.bonusDamagePercent += 10;
    }
}
