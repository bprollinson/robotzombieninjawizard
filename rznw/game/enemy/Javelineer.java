package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.JavelineerActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.MagicJavelinSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Bomb;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.MagicJavelin;
import rznw.map.element.EnemyMapElement;
import rznw.utility.RandomNumberGenerator;

public class Javelineer extends EnemyCharacterWithSpell
{
    private static final int SPELL_MAGIC_JAVELIN = 0;

    private static char mapCharacter = 'j';
    private static int numSpells = 1;

    public Javelineer(int level)
    {
        super(level, Javelineer.numSpells);
    }

    public Javelineer getNewInstance(int level)
    {
        return new Javelineer(level);
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case Javelineer.SPELL_MAGIC_JAVELIN:
                return new MagicJavelinSpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Javelineer.mapCharacter, this);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_MANA_BURN,
          EnemyCharacter.STAT_DODGE,
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          Javelineer.SPELL_MAGIC_JAVELIN
        };
    }

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new Bomb(), 1);
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
        return new EquipmentGroup(new MagicJavelin(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new JavelineerActionCalculator();
    }
}
