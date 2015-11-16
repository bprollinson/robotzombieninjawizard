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
import rznw.utility.RandomNumberGenerator;

public class MeteorShowerSpell extends Spell
{
    private KillBonusGranter killBonusGranter;

    public MeteorShowerSpell()
    {
        this.killBonusGranter = new KillBonusGranter();
    }

    public boolean canCast(MainCharacter character)
    {
        return character.getSpellPoints(1) > 0 && character.getMP() >= this.getMPCost(character);
    }

    public void cast(GameWorld gameWorld)
    {
        System.out.println("Casting Meteor Shower");

        MainCharacter character = gameWorld.getMainCharacter();

        int radius = 1 + (int)Math.floor(character.getSpellPoints(1) / 4);
        int hitProbability = 5 * character.getSpellPoints(1);

        Map map = gameWorld.getMap();
        MapElement characterElement = character.getMapElement();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);

        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            int random = RandomNumberGenerator.randomInteger(1, 100);

            EnemyCharacter enemy = (EnemyCharacter)iterator.next();

            if (random <= hitProbability)
            {
                System.out.println("Meteor hit!");

                System.out.println("Before: " + enemy.getHP());
                int damage = 50 + 50 * character.getSpellPoints(1);
                enemy.damage(damage);
                System.out.println("After: " + enemy.getHP());

                if (enemy.isDead())
                {
                    this.killBonusGranter.grantKillBonuses(character, enemy);
                    MapElement enemyMapElement = enemy.getMapElement();
                    map.setElement(enemyMapElement.getRow(), enemyMapElement.getColumn(), null);
                }
            }
            else
            {
                System.out.println("Meteor miss!");
            }
        }
    }

    public int getMPCost(MainCharacter character)
    {
        int spellLevel = character.getSpellPoints(1);
        return Math.max(200 - 10 * spellLevel, 1);
    }
}
