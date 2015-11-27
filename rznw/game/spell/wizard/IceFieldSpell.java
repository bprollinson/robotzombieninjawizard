package rznw.game.spell.wizard;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public class IceFieldSpell extends Spell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Ice Field");

        MainCharacter character = gameWorld.getMainCharacter();

        int radius = 1 + (int)Math.floor(spellPoints / 4);
        Map map = gameWorld.getMap();
        MapElement characterElement = character.getMapElement();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();

            System.out.println("Before: " + enemy.getHP());
            int damage = 20;
            enemy.damage(damage, character, gameWorld);
            System.out.println("After: " + enemy.getHP());

            if (!enemy.isDead())
            {
                int probabilityToFreeze = 5 * spellPoints;

                if (RandomNumberGenerator.rollSucceeds(probabilityToFreeze))
                {
                    System.out.println("Enemy frozen");
                    enemy.getStatusEffects().freeze();
                }
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}
