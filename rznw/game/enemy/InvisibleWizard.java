package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.RadialInvisibilitySpellActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.InvisibilitySpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.FullManaPotion;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.InvisibilityWand;
import rznw.map.element.DisappearingEnemyMapElement;

public class InvisibleWizard extends EnemyCharacterWithSpell
{
    public static final int ENEMY_NUMBER = 9;

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
          EnemyStat.STAT_MANA,
          EnemyStat.STAT_ACCURACY,
          EnemyStat.STAT_DAMAGE
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

    public InventoryItem getItemDrop()
    {
        return new FullManaPotion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new InvisibilityWand();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new RadialInvisibilitySpellActionCalculator();
    }

    public int getEnemyNumber()
    {
        return InvisibleWizard.ENEMY_NUMBER;
    }

    public String getLogName()
    {
        return "invisible wizard";
    }
}
