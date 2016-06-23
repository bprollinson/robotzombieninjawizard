package rznw.game.enemy;

import rznw.game.enemy.action.calculator.DiagonalSpellActionCalculator;
import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.MagicJavelinSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Bomb;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.MagicJavelin;
import rznw.map.element.EnemyMapElement;

public class Javelineer extends EnemyCharacterWithSpell
{
    public static final int ENEMY_NUMBER = 10;

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

    public InventoryItem getItemDrop()
    {
        return new Bomb();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new MagicJavelin();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new DiagonalSpellActionCalculator();
    }

    public int getEnemyNumber()
    {
        return Javelineer.ENEMY_NUMBER;
    }
}
