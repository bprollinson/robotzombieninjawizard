package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyProjectileSpellActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.ZapSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.MagicShield;
import rznw.game.maincharacter.inventory.ManaPotion;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class Enchanter extends EnemyCharacterWithSpell
{
    private static final int SPELL_ZAP = 0;

    private static char mapCharacter = 'e';
    private static int numSpells = 1;

    public Enchanter(int level)
    {
        super(level, Enchanter.numSpells);
    }

    public Enchanter getNewInstance(int level)
    {
        return new Enchanter(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_MANA_BURN,
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_MANA_BURN,
          EnemyCharacter.STAT_SIGHT,
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          Enchanter.SPELL_ZAP
        };
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case Enchanter.SPELL_ZAP:
                return new ZapSpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Enchanter.mapCharacter, this);
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
        return new EquipmentGroup(new MagicShield(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyProjectileSpellActionCalculator();
    }
}
