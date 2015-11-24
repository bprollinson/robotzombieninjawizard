package rznw.game.spell.ninja;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public class StunStrikeSpell extends Spell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Stun Strike");

        MainCharacter character = gameWorld.getMainCharacter();

        int startRow = character.getMapElement().getRow();
        int startColumn = character.getMapElement().getColumn();

        Map map = gameWorld.getMap();
        map.setElement(startRow, startColumn, null);

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

        int enemyRow = startRow;
        int enemyColumn = startColumn;

        while (map.getElement(enemyRow, enemyColumn) == null)
        {
            enemyRow += deltaRow;
            enemyColumn += deltaColumn;
        }

        int characterRow = enemyRow - deltaRow;
        int characterColumn = enemyColumn - deltaColumn;

        MapElement characterElement = character.getMapElement();
        characterElement.setRow(characterRow);
        characterElement.setColumn(characterColumn);
        map.setElement(characterRow, characterColumn, characterElement);

        MapElement enemyElement = map.getElement(enemyRow, enemyColumn);
        if (enemyElement instanceof EnemyMapElement)
        {
            System.out.println("Hitting enemy");
            int damage = 20 + 10 * spellPoints;
            int stunProbability = 5 * spellPoints;

            Character enemy = ((EnemyMapElement)enemyElement).getCharacter();
            System.out.println("Enemy HP before: " + enemy.getHP());
            enemy.damage(damage, character);
            System.out.println("Enemy HP after: " + enemy.getHP());

            if (RandomNumberGenerator.rollSucceeds(stunProbability))
            {
                System.out.println("Stunning enemy");
                enemy.getStatusEffects().freeze();
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
