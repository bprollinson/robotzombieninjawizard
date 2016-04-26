package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.EnemyRadiusSpellActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.VampireWaveSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.BloodSword;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Potion;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class Nosferatu extends EnemyCharacterWithSpell
{
    private static final int SPELL_VAMPIRE_WAVE = 0;

    private static char mapCharacter = 'n';
    private static int numSpells = 1;

    public Nosferatu(int level)
    {
        super(level, Nosferatu.numSpells);
    }

    public Nosferatu getNewInstance(int level)
    {
        return new Nosferatu(level);
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case Nosferatu.SPELL_VAMPIRE_WAVE:
                return new VampireWaveSpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Nosferatu.mapCharacter, this);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_MANA_BURN,
          EnemyCharacter.STAT_DAMAGE,
          EnemyCharacter.STAT_ACCURACY
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          Nosferatu.SPELL_VAMPIRE_WAVE
        };
    }

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new Potion(), 1);
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
        return new EquipmentGroup(new BloodSword(), 1);
    }

    public void damagedMainCharacter(MainCharacter mainCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Attacked by nosferatu");
        System.out.println("Nosferatu HP before: " + this.getHP());

        int healHP = (int)Math.floor(0.1 * damage);
        this.heal(healHP);
        System.out.println("Nosferatu heals by: " + healHP);
        System.out.println("Nosferatu HP after: " + this.getHP());

        this.heal(healHP);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new EnemyRadiusSpellActionCalculator();
    }
}
