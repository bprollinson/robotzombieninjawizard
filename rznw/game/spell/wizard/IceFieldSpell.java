package rznw.game.spell.wizard;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public class IceFieldSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Ice Field";
    }

    public String getDescription()
    {
        return "Damages all enemies within a radius of you, with a chance to freeze each one. Radius and chance to freeze increase as spell level increases.";
    }

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
            enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
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

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int radius = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: 20",
            "Radius: " + radius,
            "Chance to freeze: " + 5 * spellPoints + "%"
        };
    }
}
