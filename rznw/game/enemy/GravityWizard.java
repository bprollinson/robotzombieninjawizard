package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.ProjectileSpellActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.GravityBeltSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.GravityBlade;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.ManaPotion;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;

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

    public InventoryItem getItemDrop()
    {
        return new ManaPotion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new GravityBlade();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new ProjectileSpellActionCalculator();
    }
}
