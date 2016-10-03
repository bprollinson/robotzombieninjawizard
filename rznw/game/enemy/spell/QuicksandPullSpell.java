package rznw.game.enemy.spell;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapElementSetter;
import rznw.map.element.MapElement;

public class QuicksandPullSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        System.out.println("Enemy is casting quicksand pull with spell points of: " + spellPoints);

        int damage = 5 * spellPoints;
        System.out.println("Quicksand pull damage: " + damage);

        MainCharacter mainCharacter = gameWorld.getMainCharacter();
        mainCharacter.damage(damage, enemyCharacter, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);

        MapElement mainCharacterElement = mainCharacter.getMapElement();
        MapElement enemyCharacterElement = enemyCharacter.getMapElement();

        System.out.println("Main character position: " + mainCharacterElement.getRow() + ", " + mainCharacterElement.getColumn());
        System.out.println("Enemy position: " + enemyCharacterElement.getRow() + ", " + enemyCharacterElement.getColumn());

        int distance = 1 + (int)Math.floor(spellPoints / 4);

        for (int i = 0; i < distance; i++)
        {
            int deltaRow = 0;
            if (mainCharacterElement.getRow() < enemyCharacterElement.getRow())
            {
                deltaRow = 1;
            }
            if (mainCharacterElement.getRow() > enemyCharacterElement.getRow())
            {
                deltaRow = -1;
            }

            int deltaColumn = 0;
            if (mainCharacterElement.getColumn() < enemyCharacterElement.getColumn())
            {
                deltaColumn = 1;
            }
            if (mainCharacterElement.getColumn() > enemyCharacterElement.getColumn())
            {
                deltaColumn = -1;
            }

            Map map = gameWorld.getMap();

            int row = mainCharacterElement.getRow();
            int column = mainCharacterElement.getColumn();

            row += deltaRow;
            column += deltaColumn;

            MapElement test = map.getElement(row, column);
            if (test != null)
            {
                break;
            }

            map.setElement(mainCharacterElement.getRow(), mainCharacterElement.getColumn(), null);
            MapElementSetter.setElement(map, mainCharacterElement, row, column);
        }

        System.out.println("Main character final position: " + mainCharacterElement.getRow() + ", " + mainCharacterElement.getColumn());
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(50 - 10 * spellPoints, 1);
    }
}
