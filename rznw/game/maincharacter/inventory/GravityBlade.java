package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

public class GravityBlade extends Weapon
{
    private static int DISTANCE = 3;

    public String getDisplayName()
    {
        return "Gravity Blade";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "",
            "Pushes enemies away from you",
            "Push distance : 3 spaces",
            "",
            "Value: " + this.getValue()
        };
    }

    public int getDamage()
    {
        return 8;
    }

    public int getValue()
    {
        return 400;
    }

    public void damagedEnemyCharacter(MainCharacter mainCharacter, EnemyCharacter enemyCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Gravity blading opponent!");

        MapElement mainCharacterElement = mainCharacter.getMapElement();
        MapElement enemyCharacterElement = enemyCharacter.getMapElement();

        int deltaRow = 0;
        if (enemyCharacterElement.getRow() < mainCharacterElement.getRow())
        {
            deltaRow = -1;
        }
        if (enemyCharacterElement.getRow() > mainCharacterElement.getRow())
        {
            deltaRow = 1;
        }

        int deltaColumn = 0;
        if (enemyCharacterElement.getColumn() < mainCharacterElement.getColumn())
        {
            deltaColumn = -1;
        }
        if (enemyCharacterElement.getColumn() > mainCharacterElement.getColumn())
        {
            deltaColumn = 1;
        }

        Map map = gameWorld.getMap();

        int row = enemyCharacterElement.getRow();
        int column = enemyCharacterElement.getColumn();

        for (int i = 0; i < GravityBlade.DISTANCE; i++)
        {
            row += deltaRow;
            column += deltaColumn;

            MapElement test = map.getElement(row, column);
            if (test != null)
            {
                break;
            }

            map.setElement(enemyCharacterElement.getRow(), enemyCharacterElement.getColumn(), null);
            map.setElement(row, column, enemyCharacterElement);
            enemyCharacterElement.setRow(row);
            enemyCharacterElement.setColumn(column);
        }
    }
}
