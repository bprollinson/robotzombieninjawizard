package rznw.game.enemy;

import rznw.game.enemy.action.BeastSummonerActionCalculator;
import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.SummonBeastSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
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

    public InventoryItemGroup getItemDrops()
    {
        return new InventoryItemGroup(new Potion(), 1);
    }

    public EquipmentGroup getEquipmentDrops()
    {
        return new EquipmentGroup(new WandOfSummoning(), 1);
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new BeastSummonerActionCalculator();
    }
}
