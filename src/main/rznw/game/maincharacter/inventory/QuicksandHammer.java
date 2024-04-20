package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapElementSetter;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;

import java.util.Vector;

public class QuicksandHammer extends Weapon
{
    public static final int EQUIPMENT_NUMBER = 9;

    public String getDisplayName()
    {
        return "Quicksand Hammer";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "Pulls nearby enemies in on contact",
            "Pull radius: 10 squares",
            "Pull distance: 1 square",
            "",
            "Value: " + this.getValue()
        };
    }

    public int getDamage()
    {
        return 10;
    }

    public void damagedEnemyCharacter(MainCharacter mainCharacter, EnemyCharacter enemyCharacter, int damage, GameWorld gameWorld)
    {
        for (int radius = 2; radius <= 10; radius++)
        {
            this.pullEnemiesWithRadius(mainCharacter, gameWorld, radius);
        }

        LogRendererFactory.instance().log("Pulled enemies closer.");
    }

    private void pullEnemiesWithRadius(MainCharacter mainCharacter, GameWorld gameWorld, int radius)
    {
        MapElement mainCharacterMapElement = mainCharacter.getMapElement();

        Vector<EnemyMapElement> enemyElements = new Vector<EnemyMapElement>();

        Map map = gameWorld.getMap();

        for (int row = mainCharacterMapElement.getRow() - radius; row <= mainCharacterMapElement.getRow() + radius; row++)
        {
            for (int column = mainCharacterMapElement.getColumn() - radius; column <= mainCharacterMapElement.getColumn() + radius; column++)
            {
                if (row < 0 || row >= Map.NUM_ROWS || column < 0 || column >= Map.NUM_COLUMNS)
                {
                    continue;
                }

                int rowRadius = Math.abs(mainCharacterMapElement.getRow() - row);
                int columnRadius = Math.abs(mainCharacterMapElement.getColumn() - column);

                if (rowRadius != radius && columnRadius != radius)
                {
                    continue;
                }

                if (map.getElement(row, column) != null && map.getElement(row, column).isEnemy())
                {
                    enemyElements.add((EnemyMapElement)map.getElement(row, column));
                }
            }
        }

        for (int i = 0; i < enemyElements.size(); i++)
        {
            MapElement enemyMapElement = enemyElements.get(i);

            int deltaRow = 0;
            if (enemyMapElement.getRow() < mainCharacterMapElement.getRow())
            {
                deltaRow = 1;
            }
            if (enemyMapElement.getRow() > mainCharacterMapElement.getRow())
            {
                deltaRow = -1;
            }

            int deltaColumn = 0;
            if (enemyMapElement.getColumn() < mainCharacterMapElement.getColumn())
            {
                deltaColumn = 1;
            }
            if (enemyMapElement.getColumn() > mainCharacterMapElement.getColumn())
            {
                deltaColumn = -1;
            }

            int newRow = enemyMapElement.getRow() + deltaRow;
            int newColumn = enemyMapElement.getColumn() + deltaColumn;

            if (map.getElement(newRow, newColumn) == null)
            {
                map.setElement(enemyMapElement.getRow(), enemyMapElement.getColumn(), null);
                MapElementSetter.setElement(map, enemyMapElement, newRow, newColumn);
            }
        }
    }

    public int getEquipmentNumber()
    {
        return QuicksandHammer.EQUIPMENT_NUMBER;
    }
}
