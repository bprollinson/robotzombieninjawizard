package rznw.game.spell.ninja;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public class StealGoldSpell extends Spell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Steal Gold");

        MainCharacter character = gameWorld.getMainCharacter();

        int row = character.getMapElement().getRow();
        int column = character.getMapElement().getColumn();

        int deltaRow = 0;
        int deltaColumn = 0;

        switch(direction)
        {
            case Spell.DIRECTION_UP:
                deltaRow = -1;
                break;
            case Spell.DIRECTION_DOWN:
                deltaRow = 1;
                break;
            case Spell.DIRECTION_LEFT:
                deltaColumn = -1;
                break;
            case Spell.DIRECTION_RIGHT:
                deltaColumn = 1;
                break;
        }

        row += deltaRow;
        column += deltaColumn;

        Map map = gameWorld.getMap();
        MapElement element = map.getElement(row, column);

        if (element instanceof EnemyMapElement)
        {
            int stealProbability = 5 * spellPoints;

            if (RandomNumberGenerator.rollSucceeds(stealProbability))
            {
                System.out.println("Steal success");

                EnemyCharacter enemy = (EnemyCharacter)((EnemyMapElement)element).getCharacter();
                int numGoldPercent = 20 + 10 * spellPoints;
                int baseGold = enemy.getNumGold();

                int numGold = (int)Math.floor(numGoldPercent / 100.0 * baseGold);
                character.getInventory().addGold(numGold);

                System.out.println("Steal percentage: " + numGoldPercent);
                System.out.println("Base gold: " + baseGold);
                System.out.println("Gold stolen: " + numGold);
            }
            else
            {
                System.out.println("Steal failure - bad roll");
            }
        }
        else
        {
            System.out.println("Steal failure - not an enemy");
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public boolean requiresDirectionInput()
    {
        return true;
    }
}
