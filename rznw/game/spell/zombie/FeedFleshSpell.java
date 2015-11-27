package rznw.game.spell.zombie;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;

public class FeedFleshSpell extends Spell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Feed Flesh");
        MainCharacter character = gameWorld.getMainCharacter();
        Map map = gameWorld.getMap();

        MapElement characterElement = character.getMapElement();

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

        int row = characterElement.getRow() + deltaRow;
        int column = characterElement.getColumn() + deltaColumn;

        MapElement element = map.getElement(row, column);
        if (element instanceof EnemyMapElement)
        {
            int damage = 50 + 10 * spellPoints;
            int healHP = (int)Math.floor(damage / 2);

            Character enemy = ((EnemyMapElement)element).getCharacter();
            System.out.println("Before: " + enemy.getHP());
            enemy.damage(damage, character, gameWorld);
            System.out.println("After: " + enemy.getHP());
            gameWorld.getMainCharacter().heal(healHP);
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
