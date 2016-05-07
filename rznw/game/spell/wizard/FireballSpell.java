package rznw.game.spell.wizard;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;

public class FireballSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Fireball";
    }

    public String getDescription()
    {
        return "Shoot a projectile in any direction. When this projectile hits an enemy or object, it explodes, damaging all enemies close to the blast area. The blast radius increases with your spell level.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Fireball");

        MainCharacter character = gameWorld.getMainCharacter();

        int damage = 50 + 10 * spellPoints;

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(0, 0, direction);

        boolean objectFound = false;
        int row = character.getMapElement().getRow();
        int column = character.getMapElement().getColumn();

        while (!objectFound)
        {
            row += positionChange.getDeltaRow();
            column += positionChange.getDeltaColumn();

            Map map = gameWorld.getMap();
            MapElement element = map.getElement(row, column);

            if (element != null)
            {
                objectFound = true;
            }

            if (element instanceof EnemyMapElement)
            {
                System.out.println("Direct hit " + element);

                Character enemy = ((EnemyMapElement)element).getCharacter();
                System.out.println("Before: " + enemy.getHP());
                enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
                System.out.println("After: " + enemy.getHP());
            }

            if (element != null)
            {
                MapElement characterElement = character.getMapElement();

                int radius = 1 + (int)Math.floor(spellPoints / 4);
                Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(element.getRow() - radius, element.getColumn() - radius, element.getRow() + radius, element.getColumn() + radius);
                for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
                {
                    System.out.println("Indirect hit " + element);

                    EnemyCharacter enemy = (EnemyCharacter)iterator.next();
                    System.out.println("Before: " + enemy.getHP());
                    enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
                    System.out.println("After: " + enemy.getHP());
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
        int damage = 50 + 10 * spellPoints;
        int radius = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Base damage: " + damage,
            "Explosion radius: " + radius,
            "Explosion damage: " + damage
        };
    }
}
