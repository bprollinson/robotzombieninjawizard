package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.ZenithActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.HealSpell;
import rznw.game.enemy.spell.HealthZapSpell;
import rznw.game.enemy.spell.ZapSpell;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.FullManaPotion;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.ZenithWand;
import rznw.map.element.EnemyMapElement;

public class Zenith extends EnemyCharacterWithSpell
{
    public static final int ENEMY_NUMBER = 26;

    public static final int SPELL_HEALTH_ZAP = 0;
    public static final int SPELL_ZAP = 1;
    public static final int SPELL_HEAL = 2;

    private static char mapCharacter = 'z';
    private static int numSpells = 3;

    public Zenith(int level)
    {
        super(level, Zenith.numSpells);
    }

    public Zenith getNewInstance(int level)
    {
        return new Zenith(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_MANA_BURN,
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_MANA_BURN,
          EnemyCharacter.STAT_PADDING,
          EnemyCharacter.STAT_HEALTH
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          Zenith.SPELL_HEALTH_ZAP,
          Zenith.SPELL_ZAP,
          Zenith.SPELL_HEAL
        };
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case Zenith.SPELL_HEALTH_ZAP:
                return new HealthZapSpell();
            case Zenith.SPELL_ZAP:
                return new ZapSpell();
            case Zenith.SPELL_HEAL:
                return new HealSpell();
            default:
                return null;
        }
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new ZenithWand();
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Zenith.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new FullManaPotion();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new ZenithActionCalculator();
    }

    public boolean isFinalBoss()
    {
        return true;
    }

    public int getEnemyNumber()
    {
        return Zenith.ENEMY_NUMBER;
    }
}
