package rznw.map.generator;

import rznw.game.CharacterGenerator;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.Map;

import java.util.List;

public class MapGenerator
{
    private MapEnemyGenerator enemyGenerator;
    private MapTerrainGenerator terrainGenerator;
    private MapCharacterGenerator characterGenerator;
    private MapPathGenerator pathGenerator;

    public MapGenerator()
    {
        this.enemyGenerator = new MapEnemyGenerator();
        this.terrainGenerator = new MapTerrainGenerator();
        this.characterGenerator = new MapCharacterGenerator();
        this.pathGenerator = new MapPathGenerator();
    }

    public Map generate(MainCharacter character, CharacterGenerator characterGenerator)
    {
        Map map = new Map();

        List<MapArea> rooms = this.terrainGenerator.generateTerrain(map);
        this.pathGenerator.generatePaths(map, rooms);
        this.characterGenerator.placeCharacter(map, character, rooms);
        this.enemyGenerator.generateEnemies(map, characterGenerator, rooms);

        return map;
    }
}
