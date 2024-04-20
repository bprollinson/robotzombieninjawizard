package rznw.game.spell.zombie;

import rznw.game.Character;
import rznw.game.SummonedCharacter;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;

import java.util.Collection;
import java.util.Iterator;

public class ExplodeZombiesSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Explode Zombies";
    }

    public String getDescription()
    {
        return "Each zombie you control explodes, dealing damage to all enemies within a radius of it.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting explode zombies.");

        Map map = gameWorld.getMap();

        Collection<SummonedCharacter> summons = gameWorld.getMap().getSummons();
        for (Iterator iterator = summons.iterator(); iterator.hasNext();)
        {
            SummonedCharacter summon = (SummonedCharacter)iterator.next();
            MapElement summonElement = summon.getMapElement();

            LogRendererFactory.instance().log("Exploded a zombie.");
            summon.setHP(0);

            Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(summonElement.getRow() - 1, summonElement.getColumn() - 1, summonElement.getRow() + 1, summonElement.getColumn() + 1);
            for (Iterator iterator2 = enemies.iterator(); iterator2.hasNext();)
            {
                EnemyCharacter enemy = (EnemyCharacter)iterator2.next();
                MapElement enemyElement = enemy.getMapElement();

                int damage = 100 + 20 * spellPoints;
                damage = enemy.damage(damage, gameWorld.getMainCharacter(), gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
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
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + (100 + 20 * spellPoints),
            "Radius: 1"
        };
    }
}
