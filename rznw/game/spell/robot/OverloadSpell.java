package rznw.game.spell.robot;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

public class OverloadSpell extends Spell
{
    public boolean canCast(MainCharacter character)
    {
        return character.getSpellPoints(2) > 0 && character.getMP() >= this.getMPCost(character);
    }

    public void cast(GameWorld gameWorld)
    {
        System.out.println("Casting Overload");
        MainCharacter character = gameWorld.getMainCharacter();
        character.setMP(character.getMP() - this.getMPCost(character));

        MapElement characterElement = character.getMapElement();
        Map map = gameWorld.getMap();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - 1, characterElement.getColumn() - 1, characterElement.getRow() + 1, characterElement.getColumn() + 1);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            System.out.println("Before: " + enemy.getHP());
            int damage = 10 + 2 * character.getSpellPoints(2);
            enemy.damage(damage);
            System.out.println("After: " + enemy.getHP());

            if (enemy.isDead())
            {
                //killBonusGranter.grantKillBonuses(character, enemy);
                MapElement enemyMapElement = enemy.getMapElement();
                map.setElement(enemyMapElement.getRow(), enemyMapElement.getColumn(), null);
            }
        }

        character.damage(5);
    }

    private int getMPCost(MainCharacter character)
    {
        int spellLevel = character.getSpellPoints(2);
        return Math.max(20 - spellLevel, 1);
    }
}
