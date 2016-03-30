package rznw.game.spell.robot;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.enemy.Assassin;
import rznw.game.enemy.Mummy;
import rznw.game.enemy.Werewolf;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.CharacterMapElement;
import rznw.map.element.MapElement;

public class LevelUpSpell extends UndirectedSpell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Level Up");

        int radius = 1 + (int)Math.floor(spellPoints / 4);

        Map map = gameWorld.getMap();
        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);
        System.out.println(enemies.size() + " enemies affected");

        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            int levelDifference = spellPoints;
            int newLevel = enemy.getLevel() + levelDifference;

            EnemyCharacter newEnemy = null;

            if (enemy instanceof Werewolf)
            {
                newEnemy = new Werewolf(newLevel);
            }
            else if (enemy instanceof Mummy)
            {
                newEnemy = new Mummy(newLevel);
            }
            else if (enemy instanceof Assassin)
            {
                newEnemy = new Assassin(newLevel);
            }

            if (newEnemy != null)
            {
                System.out.println("Replaced enemy");
                CharacterMapElement mapElement = (CharacterMapElement)enemy.getMapElement();
                mapElement.setCharacter(newEnemy);
                newEnemy.setMapElement(mapElement);
            }
            else
            {
                System.out.println("Unsupported enemy type");
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
