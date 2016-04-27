package rznw.game.enemy;

import rznw.game.enemy.action.EnemyActionCalculator;
import rznw.game.enemy.action.QuicksandDwellerActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.QuicksandPullSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.maincharacter.inventory.QuicksandHammer;
import rznw.map.element.EnemyMapElement;

public class QuicksandDweller extends EnemyCharacterWithSpell
{
    private static final int SPELL_QUICKSAND_PULL = 0;

    private static char mapCharacter = 'q';
    private static int numSpells = 1;

    public QuicksandDweller(int level)
    {
        super(level, QuicksandDweller.numSpells);
    }

    public QuicksandDweller getNewInstance(int level)
    {
        return new QuicksandDweller(level);
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case QuicksandDweller.SPELL_QUICKSAND_PULL:
                return new QuicksandPullSpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, QuicksandDweller.mapCharacter, this);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_DAMAGE
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          QuicksandDweller.SPELL_QUICKSAND_PULL
        };
    }

    public InventoryItem getItemDrop()
    {
        return new Potion();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new QuicksandHammer();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new QuicksandDwellerActionCalculator();
    }
}
