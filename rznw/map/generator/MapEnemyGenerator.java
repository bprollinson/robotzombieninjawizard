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
    private static int ENEMY_PROBABILITY = 10;

    public void generateEnemies(Map map, CharacterGenerator characterGenerator, List<MapArea> rooms)
    {
        for (int i = 0; i < rooms.size(); i++)
        {
            MapArea room = rooms.get(i);
            for (int row = room.getStartY() + 1; row < room.getEndY(); row++)
            {
                for (int column = room.getStartX() + 1; column < room.getEndX(); column++)
                {
                    int random = RandomNumberGenerator.randomInteger(1, 100);

                    if (map.getElement(row, column) == null && random <= MapEnemyGenerator.ENEMY_PROBABILITY)
                    {
                        EnemyCharacter enemyCharacter = characterGenerator.generateEnemy();
                        enemyCharacter.generateMapElement(row, column);
                        EnemyMapElement enemyMapElement = (EnemyMapElement)enemyCharacter.getMapElement();
                        map.setElement(row, column, enemyMapElement);
                    }
                }
            }
        }
    }
}
