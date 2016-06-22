package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.RadialHealSpellActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.VampireWaveSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.BloodSword;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.Potion;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;

public class Nosferatu extends EnemyCharacterWithSpell
{
    private static final int ENEMY_NUMBER = 14;

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

    public InventoryItem getItemDrop()
    {
        return new Potion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new BloodSword();
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
        return new RadialHealSpellActionCalculator();
    }

    public int getEnemyNumber()
    {
        return Nosferatu.ENEMY_NUMBER;
    }
}
