package rznw.game.spell.wizard;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.KillBonusGranter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

public class EarthquakeSpell extends Spell
{
    private KillBonusGranter killBonusGranter;

    public EarthquakeSpell()
    {
        this.killBonusGranter = new KillBonusGranter();
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Earthquake");
        MainCharacter character = gameWorld.getMainCharacter();

        MapElement characterElement = character.getMapElement();
        Map map = gameWorld.getMap();
        int radius = 1 + (int)Math.floor(spellPoints / 2);
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            System.out.println("Before: " + enemy.getHP());
            int damage = 50 + 10 * spellPoints;
            enemy.damage(damage);
            System.out.println("After: " + enemy.getHP());

            if (enemy.isDead())
            {
                this.killBonusGranter.grantKillBonuses(character, enemy);
                MapElement enemyMapElement = enemy.getMapElement();
                map.setElement(enemyMapElement.getRow(), enemyMapElement.getColumn(), null);
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}
