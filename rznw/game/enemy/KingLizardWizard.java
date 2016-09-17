package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.ProjectileSpellActionCalculator;
import rznw.game.enemy.spell.AcidSpitSpell;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.Herb;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.ProtectivePlate;
import rznw.map.element.EnemyMapElement;

public class KingLizardWizard extends EnemyCharacterWithSpell
{
    public static final int ENEMY_NUMBER = 11;

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
          EnemyStat.STAT_MANA,
          EnemyStat.STAT_MANA_BURN,
          EnemyStat.STAT_ACCURACY
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          KingLizardWizard.SPELL_ACID_SPIT
        };
    }

    public InventoryItem getItemDrop()
    {
        return new Herb();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new ProtectivePlate();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new ProjectileSpellActionCalculator();
    }

    public int getEnemyNumber()
    {
        return KingLizardWizard.ENEMY_NUMBER;
    }
}
