package rznw.game.enemy;

import rznw.game.Character;
import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.DeathScythe;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.FullPotion;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;

public class Undertaker extends EnemyCharacter
{
    private static char mapCharacter = 'u';

    public Undertaker(int level)
    {
        super(level);
    }

    public Undertaker getNewInstance(int level)
    {
        return new Undertaker(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_PADDING,
          EnemyCharacter.STAT_ACCURACY
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Undertaker.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new FullPotion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new DeathScythe();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyMeleeActionCalculator();
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Your are being taken under!");

        int hp = mainCharacter.getHP() / 2;
        mainCharacter.damage(hp, this, gameWorld, Character.DAMAGE_SOURCE_PHYSICAL);
    }
}
