package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.FumeBeastActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.FumeCloudSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.Herb;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.PoisonCloth;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public class FumeBeast extends EnemyCharacterWithSpell
{
    private static final int SPELL_FUME_CLOUD = 0;

    private static char mapCharacter = 'f';
    private static int numSpells = 1;

    public FumeBeast(int level)
    {
        super(level, FumeBeast.numSpells);
    }

    public FumeBeast getNewInstance(int level)
    {
        return new FumeBeast(level);
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case FumeBeast.SPELL_FUME_CLOUD:
                return new FumeCloudSpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, FumeBeast.mapCharacter, this);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_DAMAGE
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          FumeBeast.SPELL_FUME_CLOUD
        };
    }

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new Herb(), 1);
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
        return new EquipmentGroup(new PoisonCloth(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new FumeBeastActionCalculator();
    }
}
