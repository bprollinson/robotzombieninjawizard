package rznw.game.spell.wizard;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

import java.util.Collection;
import java.util.Iterator;

public class MeteorShowerSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Meteor Shower";
    }

    public String getDescription()
    {
        return "Meteors rain down from the sky, crushing your enemies. The radius and concentration of the meteors varies with your spell level.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting meteor shower.");

        MainCharacter character = gameWorld.getMainCharacter();

        int radius = 1 + (int)Math.floor(spellPoints / 4);
        int hitProbability = 5 * spellPoints;

        Map map = gameWorld.getMap();
        MapElement characterElement = character.getMapElement();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);

        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();

            if (RandomNumberGenerator.rollSucceeds(hitProbability))
            {
                int damage = 50 + 50 * spellPoints;
                damage = enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
                LogRendererFactory.instance().log("Dealt " + damage + " damage to " + enemy.getLogName() + ".");
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
        int damage = 50 + 50 * spellPoints;

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Radius: " + radius,
            "Hit probability: " + 5 * spellPoints + "%",
            "Damage: " + damage
        };
    }
}
