package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyProjectileSpellActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.GravityBeltSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.GravityBlade;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.ManaPotion;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public class GravityWizard extends EnemyCharacterWithSpell
{
    private static final int SPELL_GRAVITY_BELT = 0;

    private static char mapCharacter = 'g';
    private static int numSpells = 1;

    public GravityWizard(int level)
    {
        super(level, GravityWizard.numSpells);
    }

    public GravityWizard getNewInstance(int level)
    {
        return new GravityWizard(level);
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case GravityWizard.SPELL_GRAVITY_BELT:
                return new GravityBeltSpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, GravityWizard.mapCharacter, this);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_MANA_BURN
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          GravityWizard.SPELL_GRAVITY_BELT
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
        return new EquipmentGroup(new GravityBlade(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyProjectileSpellActionCalculator();
    }
}
