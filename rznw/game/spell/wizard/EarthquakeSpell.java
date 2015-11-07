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

    public boolean canCast(MainCharacter character)
    {
        return character.getSpellPoints(2) > 0 && character.getMP() >= this.getMPCost(character);
    }

    public void cast(GameWorld gameWorld)
    {
        System.out.println("Casting Earthquake");
        MainCharacter character = gameWorld.getMainCharacter();
        character.setMP(character.getMP() - this.getMPCost(character));

        MapElement characterElement = character.getMapElement();
        Map map = gameWorld.getMap();
        int radius = 1 + (int)Math.floor(character.getSpellPoints(2) / 2);
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            System.out.println("Before: " + enemy.getHP());
            int damage = 50 + 10 * character.getSpellPoints(2);
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

    private int getMPCost(MainCharacter character)
    {
        int spellLevel = character.getSpellPoints(2);
        return Math.max(200 - 10 * spellLevel, 1);
    }
}
