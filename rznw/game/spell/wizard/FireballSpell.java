package rznw.game.spell.wizard;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.KillBonusGranter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;

public class FireballSpell extends Spell
{
    private KillBonusGranter killBonusGranter;

    public FireballSpell()
    {
        this.killBonusGranter = new KillBonusGranter();
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Fireball");

        MainCharacter character = gameWorld.getMainCharacter();

        int damage = 50 + 10 * spellPoints;

        int deltaRow = 0;
        int deltaColumn = 0;

        switch(direction)
        {
            case Spell.DIRECTION_UP:
                deltaRow = -1;
                break;
            case Spell.DIRECTION_DOWN:
                deltaRow = 1;
                break;
            case Spell.DIRECTION_LEFT:
                deltaColumn = -1;
                break;
            case Spell.DIRECTION_RIGHT:
                deltaColumn = 1;
                break;
        }

        boolean objectFound = false;
        int row = character.getMapElement().getRow();
        int column = character.getMapElement().getColumn();

        while (!objectFound)
        {
            row += deltaRow;
            column += deltaColumn;

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
                enemy.damage(damage);
                System.out.println("After: " + enemy.getHP());

                if (enemy.isDead())
                {
                    this.killBonusGranter.grantKillBonuses(character, enemy);
                    map.setElement(element.getRow(), element.getColumn(), null);
                }
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
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public boolean requiresDirectionInput()
    {
        return true;
    }
}
