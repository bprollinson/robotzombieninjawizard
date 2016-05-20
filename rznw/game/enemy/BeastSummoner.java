package rznw.game.enemy;

import rznw.game.enemy.action.calculator.BeastSummonerActionCalculator;
import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.SummonBeastSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.maincharacter.inventory.WandOfSummoning;
import rznw.map.element.EnemyMapElement;

public class BeastSummoner extends EnemyCharacterWithSpell
{
    private static final int SPELL_SUMMON_BEAST = 0;

    private static char mapCharacter = 'b';
    private static int numSpells = 1;

    public BeastSummoner(int level)
    {
        super(level, BeastSummoner.numSpells);
    }

    public BeastSummoner getNewInstance(int level)
    {
        return new BeastSummoner(level);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_SIGHT,
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_DAMAGE
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          BeastSummoner.SPELL_SUMMON_BEAST
        };
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case BeastSummoner.SPELL_SUMMON_BEAST:
                return new SummonBeastSpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, BeastSummoner.mapCharacter, this);
    }

    public InventoryItem getItemDrop()
    {
        return new Potion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new WandOfSummoning();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new BeastSummonerActionCalculator();
    }
}
