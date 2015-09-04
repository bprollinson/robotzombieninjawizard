package rznw.map;

import rznw.game.CharacterGenerator;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MainCharacterMapElement;
import rznw.map.element.Wall;
import rznw.ui.MainGamePanel;

import java.util.Random;

public class MapGenerator
{
    private static int MIN_ROOM_SIZE = 5;
    private static int MAX_ROOM_SIZE = 15;

    public Map generate(MainCharacter character, CharacterGenerator characterGenerator)
    {
        Map map = new Map();

        this.generateTerrain(map);
        this.placeCharacter(map, character);
        this.generateEnemies(map, characterGenerator);

        return map;
    }

    private void generateTerrain(Map map)
    {
        OpenArea largestOpenArea = new OpenArea(0, 0, Map.NUM_COLUMNS - 1, Map.NUM_ROWS - 1);

        while (true)
        {
            int width = largestOpenArea.getEndX() - largestOpenArea.getStartX() + 1;
            int height = largestOpenArea.getEndY() - largestOpenArea.getStartY() + 1;
            int radius = Math.min(width, height);
            if (radius < MapGenerator.MIN_ROOM_SIZE) {
                break;
            }

            this.generateRoom(map, largestOpenArea, radius);
            largestOpenArea = this.calculateLargestOpenArea(map);
        }
    }

    private void generateRoom(Map map, OpenArea largestOpenArea, int maxWidth)
    {
        maxWidth = Math.min(maxWidth, MapGenerator.MAX_ROOM_SIZE);
        int width = this.randomInteger(MapGenerator.MIN_ROOM_SIZE, maxWidth);
        int height = this.randomInteger(MapGenerator.MIN_ROOM_SIZE, maxWidth);

        int startX = this.randomInteger(largestOpenArea.getStartX(), largestOpenArea.getEndX() - width + 1);
        int startY = this.randomInteger(largestOpenArea.getStartY(), largestOpenArea.getEndY() - height + 1);
        int endX = startX + width - 1;
        int endY = startY + height - 1;

        this.renderRoom(map, startX, startY, endX, endY);
    }

    private int randomInteger(int minValue, int maxValue)
    {
        Random random = new Random();
        int offset = random.nextInt(maxValue - minValue + 1);

        return minValue + offset;
    }

    private void renderRoom(Map map, int startX, int startY, int endX, int endY)
    {
        for (int i = startX; i <= endX; i++)
        {
            map.setElement(startY, i, new Wall(startY, i));
            map.setElement(endY, i, new Wall(endY, i));
        }

        for (int i = startY; i <= endY; i++)
        {
            map.setElement(i, startX, new Wall(i, startX));
            map.setElement(i, endX, new Wall(i, endX));
        }
    }

    private OpenArea calculateLargestOpenArea(Map map)
    {
        for (int width = Map.NUM_ROWS; width >= 0; width--)
        {
            for (int row = 0; row < Map.NUM_ROWS - width; row++) {
                for (int column = 0; column < Map.NUM_COLUMNS - width; column++) {

                    OpenArea openArea = new OpenArea(column, row, column + width - 1, row + width - 1);
                    if (!this.elementExistsWithinRectangle(map, openArea))
                    {
                        return openArea;
                    }
                }
            }
        }

        return null;
    }

    private boolean elementExistsWithinRectangle(Map map, OpenArea openArea)
    {
        for (int row = openArea.getStartY(); row <= openArea.getEndY(); row++)
        {
            for (int column = openArea.getStartX(); column <= openArea.getEndX(); column++)
            {
                if (map.getElement(row, column) != null)
                {
                    return true;
                }
            }
        }

        return false;
    }

    private void placeCharacter(Map map, MainCharacter character)
    {
        character.generateMapElement(1, 1);
        MainCharacterMapElement characterMapElement = (MainCharacterMapElement)character.getMapElement();
        map.setElement(1, 1, characterMapElement);
    }

    private void generateEnemies(Map map, CharacterGenerator characterGenerator)
    {
        for (int i = 10; i < 15; i++)
        {
            EnemyCharacter enemyCharacter = characterGenerator.generateEnemy();
            enemyCharacter.generateMapElement(i, i);
            EnemyMapElement enemyMapElement = (EnemyMapElement)enemyCharacter.getMapElement();
            map.setElement(i, i, enemyMapElement);
        }
    }
}
