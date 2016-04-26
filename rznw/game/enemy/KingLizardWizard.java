package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyProjectileSpellActionCalculator;
import rznw.game.enemy.spell.AcidSpitSpell;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.Herb;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.ProtectivePlate;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class KingLizardWizard extends EnemyCharacterWithSpell
{
    private static final int SPELL_ACID_SPIT = 0;

    private static char mapCharacter = 'k';
    private static int numSpells = 1;

    public KingLizardWizard(int level)
    {
        super(level, KingLizardWizard.numSpells);
    }

    public KingLizardWizard getNewInstance(int level)
    {
        return new KingLizardWizard(level);
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case KingLizardWizard.SPELL_ACID_SPIT:
                return new AcidSpitSpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, KingLizardWizard.mapCharacter, this);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_MANA_BURN,
          EnemyCharacter.STAT_ACCURACY
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          KingLizardWizard.SPELL_ACID_SPIT
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
        return new EquipmentGroup(new ProtectivePlate(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyProjectileSpellActionCalculator();
    }
}
