package rznw.map;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.skill.Skill;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;

import java.util.Vector;

public class MapDetectVitalityScraper
{
    private GameWorld gameWorld;

    public MapDetectVitalityScraper(GameWorld gameWorld)
    {
        this.gameWorld = gameWorld;
    }

    public Vector<EnemyMapElement> getVisibleEnemies()
    {
        Vector<EnemyMapElement> enemies = new Vector<EnemyMapElement>();

        Map map = this.gameWorld.getMap();
        MainCharacter character = this.gameWorld.getMainCharacter();

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);

                boolean visible = map.isVisible(row, column);
                int skillPoints = character.getSkills().getSkillPoints(Skill.SKILL_DETECT_VITALITY);
                int radius = 1 + skillPoints;
                MapElement characterElement = character.getMapElement();
                int distance = Math.max(Math.abs(characterElement.getRow() - row), Math.abs(characterElement.getColumn() - column));

                if (element != null && element.isEnemy() && (visible || distance <= radius)) {
                    enemies.add((EnemyMapElement)element);
                }
            }
        }

        return enemies;
    }
}
