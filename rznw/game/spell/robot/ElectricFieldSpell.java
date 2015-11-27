package rznw.game.spell.robot;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

public class ElectricFieldSpell extends Spell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Electric Field");

        Map map = gameWorld.getMap();

        int radius = 1 + (int)Math.floor(spellPoints / 4);
        System.out.println("Radius is: " + radius);

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

        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();

        System.out.println("Character position is: " + characterElement.getRow() + ", " + characterElement.getColumn());

        int row = characterElement.getRow() + deltaRow * (radius + 1);
        int column = characterElement.getColumn() + deltaColumn * (radius + 1);

        System.out.println("Center row and column are: " + row + ", " + column);

        int damage = 100 + 20 * spellPoints;

        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            System.out.println("Damaging an enemy");

            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            System.out.println("Enemy HP before: " + enemy.getHP());
            enemy.damage(damage, gameWorld.getMainCharacter(), gameWorld);
            System.out.println("Enemy HP after: " + enemy.getHP());
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
