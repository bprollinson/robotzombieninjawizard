package rznw.map;

import rznw.game.CharacterGenerator;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.element.CharacterMapElement;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.Wall;
import rznw.ui.MainGamePanel;

public class MapGenerator
{
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
        for (int i = 0; i < Map.NUM_COLUMNS; i++)
        {
            map.setElement(0, i, new Wall(0, 1));
            map.setElement(Map.NUM_ROWS - 1, i, new Wall(Map.NUM_ROWS - 1, i));
        }

        for (int i = 1; i < Map.NUM_ROWS - 1; i++)
        {
            map.setElement(i, 0, new Wall(i, 0));
            map.setElement(i, Map.NUM_COLUMNS - 1, new Wall(i, Map.NUM_COLUMNS - 1));
        }
    }

    private void placeCharacter(Map map, MainCharacter character)
    {
        character.generateMapElement(1, 1);
        CharacterMapElement characterMapElement = (CharacterMapElement)character.getMapElement();
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