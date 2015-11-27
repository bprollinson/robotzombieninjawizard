package rznw.game.spell.ninja;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.map.element.Projectile;

public class ShurikenStarSpell extends Spell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Shuriken Star");
        MainCharacter character = gameWorld.getMainCharacter();

        int shurikensRemaining = 8;
        Projectile[] projectiles = new Projectile[8];
        for (int i = 0; i < projectiles.length; i++)
        {
            projectiles[i] = new Projectile(character.getMapElement().getRow(), character.getMapElement().getColumn());
        }
        int[] deltaRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] deltaColumn = {-1, 0, 1, -1, 1, -1, 0, 1};

        Map map = gameWorld.getMap();

        while (shurikensRemaining > 0)
        {
            for (int i = 0; i < projectiles.length; i++)
            {
                Projectile projectile = projectiles[i];
                if (projectile == null)
                {
                    continue;
                }

                projectile.setRow(projectile.getRow() + deltaRow[i]);
                projectile.setColumn(projectile.getColumn() + deltaColumn[i]);

                MapElement collisionElement = map.getElement(projectile.getRow(), projectile.getColumn());
                if (collisionElement != null)
                {
                    if (collisionElement instanceof EnemyMapElement)
                    {
                        System.out.println("Shuriken Star hit: " + collisionElement.getRow() + "," + collisionElement.getColumn());
                        int damage = 60 + 15 * spellPoints;
                        Character enemy = ((EnemyMapElement)collisionElement).getCharacter();
                        enemy.damage(damage, character, gameWorld);
                    } else {
                        System.out.println("Shuriken Star miss: " + collisionElement.getRow() + "," + collisionElement.getColumn());
                    }

                    shurikensRemaining--;
                    projectiles[i] = null;
                }
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}
