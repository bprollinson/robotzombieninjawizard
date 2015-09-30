package rznw.map.generator;

import rznw.game.CharacterGenerator;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.Map;
import rznw.map.generator.area.MapArea;

import java.util.List;

public class MapGenerator
{
    private MapEnemyGenerator enemyGenerator;
    private MapTerrainGenerator terrainGenerator;
    private MapCharacterGenerator characterGenerator;
    private MapStairsGenerator stairsGenerator;
    private MapPathGenerator pathGenerator;

    public MapGenerator()
    {
        this.enemyGenerator = new MapEnemyGenerator();
        this.terrainGenerator = new MapTerrainGenerator();
        this.characterGenerator = new MapCharacterGenerator();
        this.stairsGenerator = new MapStairsGenerator();
        this.pathGenerator = new MapPathGenerator();
    }

    public Map generate(MainCharacter character, CharacterGenerator characterGenerator, int level)
    {
        Map map = new Map(level);

        List<MapArea> rooms = this.terrainGenerator.generateTerrain(map);
        this.pathGenerator.generatePaths(map, rooms);
        this.characterGenerator.placeCharacter(map, character, rooms);
        this.stairsGenerator.placeStairs(map, rooms);
        this.enemyGenerator.generateEnemies(map, characterGenerator, rooms);

        return map;
    }
}
