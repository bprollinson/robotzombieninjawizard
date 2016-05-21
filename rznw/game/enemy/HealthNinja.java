package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.EscapeAndHealActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.SuperHealSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.FullPotion;
import rznw.game.maincharacter.inventory.HealShield;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.map.element.EnemyMapElement;

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

    public InventoryItem getItemDrop()
    {
        return new FullPotion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new HealShield();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EscapeAndHealActionCalculator();
    }
}
