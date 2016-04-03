package rznw.game.enemy;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.IceRod;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.SanityDrop;
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

    public boolean isDroppingItems(MainCharacter mainCharacter)
    {
        int probability = 50 + 2 * mainCharacter.getSkillPoints(6);
        probability += this.getStatusEffects().getBonusDropProbability();
        System.out.println("Item drop probability: " + probability);

        return RandomNumberGenerator.rollSucceeds(probability);
    }

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new SanityDrop(), 1);
    }

    public boolean isDroppingEquipment()
    {
        int probability = 10;
        probability += this.getStatusEffects().getBonusDropProbability();
        System.out.println("Equipment drop probability: " + probability);

        return RandomNumberGenerator.rollSucceeds(probability);
    }

    public EquipmentGroup getEquipmentDrops()
    {
        return new EquipmentGroup(new IceRod(), 1);
    }

    public void damagedMainCharacter(MainCharacter mainCharacter)
    {
        System.out.println("Struck by the yeti");

        if (RandomNumberGenerator.rollSucceeds(50))
        {
            System.out.println("You are frozen");
            mainCharacter.getStatusEffects().freeze();
        }
    }
}
