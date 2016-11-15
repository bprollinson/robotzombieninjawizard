package rznw.game.spell.robot;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.CharacterMapElement;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;
import rznw.utility.StringUtils;

import java.util.Collection;
import java.util.Iterator;

public class LevelDownSpell extends UndirectedSpell
{
    private StringUtils stringUtils = new StringUtils();

    public String getDisplayName()
    {
        return "Level Down";
    }

    public String getDescription()
    {
        return "Levels down all enemies within a radius of you. This decreases their power, but also decreases the rewards achieved for defeating them.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting level down.");

        int radius = 1 + (int)Math.floor(spellPoints / 4);

        Map map = gameWorld.getMap();
        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);

        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            if (enemy.getLevel() > 0)
            {
                int levelDifference = spellPoints;
                int newLevel = Math.max(enemy.getLevel() - levelDifference, 0);

                EnemyCharacter newEnemy = enemy.getNewInstance(newLevel);

                LogRendererFactory.instance().log("Setting " + enemy.getLogName() + " to level " + newLevel + ".");
                CharacterMapElement mapElement = enemy.getMapElement();
                mapElement.setCharacter(newEnemy);
                newEnemy.setMapElement(mapElement);
            }
            else
            {
                LogRendererFactory.instance().log(this.stringUtils.UCFirst(enemy.getLogName()) + " not affected.");
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int radius = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Levels: " + spellPoints,
            "Radius: " + radius
        };
    }
}
