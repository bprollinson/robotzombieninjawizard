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
    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Overload");
        MainCharacter character = gameWorld.getMainCharacter();

        MapElement characterElement = character.getMapElement();
        Map map = gameWorld.getMap();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - 1, characterElement.getColumn() - 1, characterElement.getRow() + 1, characterElement.getColumn() + 1);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            System.out.println("Before: " + enemy.getHP());
            int damage = 100 + 20 * spellPoints;
            enemy.damage(damage, character, gameWorld);
            System.out.println("After: " + enemy.getHP());
        }

        character.damage(50, character, gameWorld);
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}
