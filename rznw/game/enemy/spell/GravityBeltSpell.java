package rznw.game.enemy.spell;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

public class GravityBeltSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        System.out.println("Enemy is casting zap with spell points of: " + spellPoints);

        int damage = 40 + 10 * spellPoints;
        MainCharacter mainCharacter = gameWorld.getMainCharacter();
        mainCharacter.damage(damage, enemyCharacter, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);

        MapElement mainCharacterElement = mainCharacter.getMapElement();
        MapElement enemyCharacterElement = enemyCharacter.getMapElement();

        int deltaRow = 0;
        if (mainCharacterElement.getRow() < enemyCharacterElement.getRow())
        {
            deltaRow = -1;
        }
        if (mainCharacterElement.getRow() > enemyCharacterElement.getRow())
        {
            deltaRow = 1;
        }

        int deltaColumn = 0;
        if (mainCharacterElement.getColumn() < enemyCharacterElement.getColumn())
        {
            deltaColumn = -1;
        }
        if (mainCharacterElement.getColumn() > enemyCharacterElement.getColumn())
        {
            deltaColumn = 1;
        }

        Map map = gameWorld.getMap();

        int row = mainCharacterElement.getRow();
        int column = mainCharacterElement.getColumn();

        int distance = 2 + (int)Math.floor(spellPoints / 4);

        for (int i = 0; i < distance; i++)
        {
            row += deltaRow;
            column += deltaColumn;

            MapElement test = map.getElement(row, column);
            if (test != null)
            {
                break;
            }

            map.setElement(mainCharacterElement.getRow(), mainCharacterElement.getColumn(), null);
            map.setElement(row, column, mainCharacterElement);
            mainCharacterElement.setRow(row);
            mainCharacterElement.setColumn(column);
        }
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}
