package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyProjectileSpellActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.ParticleOfDarknessSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.FullPotion;
import rznw.game.maincharacter.inventory.GauntletOfDarkness;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

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
        return new EquipmentGroup(new GauntletOfDarkness(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyProjectileSpellActionCalculator();
    }
}
