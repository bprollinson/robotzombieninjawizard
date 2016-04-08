package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.HealthNinjaActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.SuperHealSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.FullPotion;
import rznw.game.maincharacter.inventory.HealShield;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class HealthNinja extends EnemyCharacterWithSpell
{
    private static final int SPELL_SUPER_HEAL = 0;

    private static char mapCharacter = 'h';
    private static int numSpells = 1;

    public HealthNinja(int level)
    {
        super(level, HealthNinja.numSpells);
    }

    public HealthNinja getNewInstance(int level)
    {
        return new HealthNinja(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_HEALTH,
          EnemyCharacter.STAT_HEALTH,
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_ACCURACY
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          HealthNinja.SPELL_SUPER_HEAL
        };
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case HealthNinja.SPELL_SUPER_HEAL:
                return new SuperHealSpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, HealthNinja.mapCharacter, this);
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
        return new InventoryItemGroup(new FullPotion(), 1);
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
        return new EquipmentGroup(new HealShield(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new HealthNinjaActionCalculator();
    }
}
