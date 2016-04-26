package rznw.game.enemy;

import rznw.game.Character;
import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyMeleeActionCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.DragonPlate;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.ManaPotion;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class Dragon extends EnemyCharacter
{
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
          EnemyCharacter.STAT_HEALTH,
          EnemyCharacter.STAT_PADDING,
          EnemyCharacter.STAT_PADDING,
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_ACCURACY
        };
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Dragon.mapCharacter, this);
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
        return new InventoryItemGroup(new ManaPotion(), 1);
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
        return new EquipmentGroup(new DragonPlate(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyMeleeActionCalculator();
    }

    public void damagedByMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("The dragon retaliates by shooting fire");
        mainCharacter.damage(10, mainCharacter, gameWorld, Character.DAMAGE_SOURCE_OTHER);
    }
}