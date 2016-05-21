package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.ProjectileSpellActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.ParticleOfDarknessSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.FullPotion;
import rznw.game.maincharacter.inventory.GauntletOfDarkness;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.map.element.EnemyMapElement;

public class Oni extends EnemyCharacterWithSpell
{
    private static final int SPELL_PARTICLE_OF_DARKNESS = 0;

    private static char mapCharacter = 'o';
    private static int numSpells = 1;

    public Oni(int level)
    {
        super(level, Oni.numSpells);
    }

    public Oni getNewInstance(int level)
    {
        return new Oni(level);
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case Oni.SPELL_PARTICLE_OF_DARKNESS:
                return new ParticleOfDarknessSpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Oni.mapCharacter, this);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_MANA
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          Oni.SPELL_PARTICLE_OF_DARKNESS
        };
    }

    public InventoryItem getItemDrop()
    {
        return new FullPotion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new GauntletOfDarkness();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new ProjectileSpellActionCalculator();
    }
}
