package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.QuicksandDwellerActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.QuicksandPullSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.maincharacter.inventory.QuicksandHammer;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class QuicksandDweller extends EnemyCharacterWithSpell
{
    private static final int SPELL_QUICKSAND_PULL = 0;

    private static char mapCharacter = 'q';
    private static int numSpells = 1;

    public QuicksandDweller(int level)
    {
        super(level, QuicksandDweller.numSpells);
    }

    public QuicksandDweller getNewInstance(int level)
    {
        return new QuicksandDweller(level);
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case QuicksandDweller.SPELL_QUICKSAND_PULL:
                return new QuicksandPullSpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, QuicksandDweller.mapCharacter, this);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_DAMAGE
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          QuicksandDweller.SPELL_QUICKSAND_PULL
        };
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
        return new InventoryItemGroup(new Potion(), 1);
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
        return new EquipmentGroup(new QuicksandHammer(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new QuicksandDwellerActionCalculator();
    }
}
