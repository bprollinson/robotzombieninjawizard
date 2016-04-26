package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.InvisibleWizardActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.InvisibilitySpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.FullManaPotion;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.InvisibilityWand;
import rznw.map.element.DisappearingEnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class InvisibleWizard extends EnemyCharacterWithSpell
{
    private static final int SPELL_INVISIBILITY = 0;

    private static char mapCharacter = 'i';
    private static int numSpells = 1;

    public InvisibleWizard(int level)
    {
        super(level, InvisibleWizard.numSpells);
    }

    public InvisibleWizard getNewInstance(int level)
    {
        return new InvisibleWizard(level);
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
          InvisibleWizard.SPELL_INVISIBILITY
        };
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case InvisibleWizard.SPELL_INVISIBILITY:
                return new InvisibilitySpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new DisappearingEnemyMapElement(row, column, InvisibleWizard.mapCharacter, this);
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
        return new InventoryItemGroup(new FullManaPotion(), 1);
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
        return new EquipmentGroup(new InvisibilityWand(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new InvisibleWizardActionCalculator();
    }
}