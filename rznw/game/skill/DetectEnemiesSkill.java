package rznw.game.skill;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

import java.util.Collection;
import java.util.Iterator;

public class DetectEnemiesSkill extends Skill
{
    public String getDisplayName()
    {
        return "Detect Enemies";
    }

    public String getDescription()
    {
        return "Detects the position of enemies.";
    }

    public boolean canUse(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        return character.getSkills().getSkillPoints(Skill.SKILL_DETECT_ENEMIES) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        LogRendererFactory.instance().log("Using detect enemies.");

        int successProbability = 20 + 5 * gameWorld.getMainCharacter().getSkills().getSkillPoints(Skill.SKILL_DETECT_ENEMIES);
        if (!RandomNumberGenerator.rollSucceeds(successProbability))
        {
            return;
        }

        MainCharacter character = gameWorld.getMainCharacter();
        MapElement characterElement = character.getMapElement();

        int maxDistance = 2 * character.getSkills().getSkillPoints(Skill.SKILL_DETECT_ENEMIES);

        Map map = gameWorld.getMap();

        Collection<EnemyCharacter> enemies = map.getEnemies();
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            MapElement enemyElement = enemy.getMapElement();

            double distance = Math.sqrt(Math.pow(enemyElement.getRow() - characterElement.getRow(), 2) + Math.pow(enemyElement.getColumn() - characterElement.getColumn(), 2));

            if (distance <= maxDistance)
            {
                for (int row = enemyElement.getRow() - 1; row <= enemyElement.getRow() + 1; row++)
                {
                    for (int column = enemyElement.getColumn() - 1; column <= enemyElement.getColumn() + 1; column++)
                    {
                        map.setVisible(character, row, column);
                    }
                }
            }
        }

        LogRendererFactory.instance().log("Detected enemies.");
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        int successProbability = 20 + 5 * skillPoints;

        return new String[] {
            "Success probability: " + successProbability + "%",
            "Maximum distance: " + 2 * skillPoints
        };
    }
}
