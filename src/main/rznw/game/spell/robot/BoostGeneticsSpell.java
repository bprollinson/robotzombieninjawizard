package rznw.game.spell.robot;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;

import java.util.Collection;
import java.util.Iterator;

public class BoostGeneticsSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Boost Genetics";
    }

    public String getDescription()
    {
        return "Increases the drop rate of enemies within a radius of you. This increases both the amount of gold and the frequency of items and equipment dropped.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting boost genetics.");

        int radius = 1 + (int)Math.floor(spellPoints / 4);

        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();
        Map map = gameWorld.getMap();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);

        int bonusDropProbability = 2 * spellPoints;
        int bonusGoldPercent = 5 * spellPoints;

        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        { 
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            enemy.getStatusEffects().enableBoostGenetics(bonusDropProbability, bonusGoldPercent);
            LogRendererFactory.instance().log("Boost genetics affects " + enemy.getLogName() + ".");
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
            "Drop rate increase: " + (2 * spellPoints) + "%",
            "Bonus gold: " + (5 * spellPoints) + "%",
            "Radius: " + radius
        };
    }
}
