package rznw.map.generator;

import rznw.game.CharacterGenerator;
import rznw.game.enemy.EnemyCharacter;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.generator.area.MapArea;
import rznw.utility.RandomNumberGenerator;

import java.util.List;

public class MapEnemyGenerator
{
    private static int BASE_ENEMY_PROBABILITY = 5;
    private static int ENEMY_PROBABILITY_PER_LEVEL = 1;

    public void generateEnemies(Map map, CharacterGenerator characterGenerator, List<MapArea> rooms)
    {
        if (map.isLastLevel())
        {
            this.generateEndBoss(map, characterGenerator, rooms);
        }

        for (int i = 0; i < rooms.size(); i++)
        {
            MapArea room = rooms.get(i);
            for (int row = room.getStartY() + 1; row < room.getEndY(); row++)
            {
                for (int column = room.getStartX() + 1; column < room.getEndX(); column++)
                {
                    if (map.getElement(row, column) == null && RandomNumberGenerator.rollSucceeds(this.getEnemyProbability(map.getLevel())))
                    {
                        EnemyCharacter enemyCharacter = characterGenerator.generateEnemy(map.getLevel());
                        enemyCharacter.generateMapElement(row, column);
                        EnemyMapElement enemyMapElement = (EnemyMapElement)enemyCharacter.getMapElement();
                        map.setElement(row, column, enemyMapElement);
                    }
                }
            }
        }
    }

    private int getEnemyProbability(int level)
    {
        return MapEnemyGenerator.BASE_ENEMY_PROBABILITY + (level - 1) * MapEnemyGenerator.ENEMY_PROBABILITY_PER_LEVEL;
    }

    private void generateEndBoss(Map map, CharacterGenerator characterGenerator, List<MapArea> rooms)
    {
        int roomIndex = RandomNumberGenerator.randomInteger(0, rooms.size() - 1);
        MapArea room = rooms.get(roomIndex);

        boolean done = false;
        int row = 0;
        int column = 0;

        while (!done)
        {
            row = RandomNumberGenerator.randomInteger(room.getStartY() + 1, room.getEndY() - 1);
            column = RandomNumberGenerator.randomInteger(room.getStartX() + 1, room.getEndX() + 1);

            if (map.getElement(row, column) == null)
            {
                done = true;
            }
        }

        EnemyCharacter endBoss = characterGenerator.generateEndBoss();
        endBoss.generateMapElement(row, column);
        EnemyMapElement endBossElement = (EnemyMapElement)endBoss.getMapElement();
        map.setElement(row, column, endBossElement);
    }
}
