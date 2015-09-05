package rznw.map.generator;

import rznw.game.CharacterGenerator;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.Map;
import rznw.map.element.MainCharacterMapElement;
import rznw.utility.RandomNumberGenerator;

import java.util.List;

public class MapGenerator
{
    private MapEnemyGenerator enemyGenerator;
    private MapTerrainGenerator terrainGenerator;

    public MapGenerator()
    {
        this.enemyGenerator = new MapEnemyGenerator();
        this.terrainGenerator = new MapTerrainGenerator();
    }

    public Map generate(MainCharacter character, CharacterGenerator characterGenerator)
    {
        Map map = new Map();

        List<MapArea> rooms = this.terrainGenerator.generateTerrain(map);
        this.placeCharacter(map, character, rooms);
        this.enemyGenerator.generateEnemies(map, characterGenerator, rooms);

        return map;
    }

    private void placeCharacter(Map map, MainCharacter character, List<MapArea> rooms)
    {
        int roomIndex = RandomNumberGenerator.randomInteger(0, rooms.size() - 1);
        MapArea room = rooms.get(roomIndex);
        int posX = RandomNumberGenerator.randomInteger(room.getStartX() + 1, room.getEndX() - 1);
        int posY = RandomNumberGenerator.randomInteger(room.getStartY() + 1, room.getEndY() - 1);

        character.generateMapElement(posY, posX);
        MainCharacterMapElement characterMapElement = (MainCharacterMapElement)character.getMapElement();
        map.setElement(posY, posX, characterMapElement);
    }
}
