package rznw.game.enemy;

import rznw.game.enemy.action.calculator.EnemyActionCalculator;
import rznw.game.enemy.action.calculator.RadialPoisonSpellActionCalculator;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.enemy.spell.FumeCloudSpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.Herb;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.PoisonCloth;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;

public class FumeBeast extends EnemyCharacterWithSpell
{
    public static final int ENEMY_NUMBER = 6;

    private static final int SPELL_FUME_CLOUD = 0;

    private static char mapCharacter = 'f';
    private static int numSpells = 1;

    public FumeBeast(int level)
    {
        super(level, FumeBeast.numSpells);
    }

    public FumeBeast getNewInstance(int level)
    {
        return new FumeBeast(level);
    }

    public EnemySpell getSpell(int index)
    {
        switch (index)
        {
            case FumeBeast.SPELL_FUME_CLOUD:
                return new FumeCloudSpell();
            default:
                return null;
        }
    }

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, FumeBeast.mapCharacter, this);
    }

    public int[] getStatSequence()
    {
        return new int[]{
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_MANA,
          EnemyCharacter.STAT_ACCURACY,
          EnemyCharacter.STAT_DAMAGE
        };
    }

    public int[] getSpellSequence()
    {
        return new int[]{
          FumeBeast.SPELL_FUME_CLOUD
        };
    }

    public InventoryItem getItemDrop()
    {
        return new Herb();
    }

    public EquipmentItem getEquipmentDrop()
    {
        return new PoisonCloth();
    }

    public EnemyActionCalculator getActionCalculator()
    {
        return new RadialPoisonSpellActionCalculator();
    }

    public int getEnemyNumber()
    {
        return FumeBeast.ENEMY_NUMBER;
    }
}
