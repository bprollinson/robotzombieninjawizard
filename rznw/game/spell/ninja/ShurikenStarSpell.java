package rznw.game.spell.ninja;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.map.element.Projectile;
import rznw.ui.LogRendererFactory;

public class ShurikenStarSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Shuriken Star";
    }

    public String getDescription()
    {
        return "Throws a projectile in all eight directions.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting shuriken star.");
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
                    if (collisionElement != null && collisionElement.isEnemy())
                    {
                        int damage = 60 + 15 * spellPoints;
                        Character enemy = ((EnemyMapElement)collisionElement).getCharacter();
                        damage = enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
                        LogRendererFactory.instance().log("Dealt " + damage + " damage to " + enemy.getLogName() + ".");
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

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + (60 + 15 * spellPoints),
        };
    }
}
