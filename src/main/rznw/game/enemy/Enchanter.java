package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.ProjectileSpellActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.ZapSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.MagicShield;
import rznw.game.maincharacter.inventory.ManaPotion;
import rznw.map.element.EnemyMapElement;

public class Enchanter extends EnemyCharacterWithSpell
{
    public static final int ENEMY_NUMBER = 5;

    private static final int SPELL_ZAP = 0;

    private static char mapCharacter = 'e';
    private static int numSpells = 1;

    public Enchanter(int level)
    {
        super(level, Enchanter.numSpells);
    }

    public Enchanter getNewInstance(int level)
    {
        return new Enchanter(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyStat.STAT_MANA,
          EnemyStat.STAT_MANA_BURN,
          EnemyStat.STAT_MANA,
          EnemyStat.STAT_MANA_BURN,
          EnemyStat.STAT_SIGHT
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          Enchanter.SPELL_ZAP
        };
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case Enchanter.SPELL_ZAP:
                return new ZapSpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Enchanter.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new ManaPotion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new MagicShield();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new ProjectileSpellActionCalculator();
    }

    public int getEnemyNumber()
    {
        return Enchanter.ENEMY_NUMBER;
    }

    public String getLogName()
    {
        return "enchanter";
    }
}
