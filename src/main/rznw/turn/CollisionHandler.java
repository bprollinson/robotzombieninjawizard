package rznw.turn;

import rznw.game.Character;
import rznw.map.Map;
import rznw.map.element.CharacterMapElement;
import rznw.map.element.MapElement;
import rznw.map.element.RockWall;
import rznw.map.GameWorld;
import rznw.turn.positionchange.PositionChange;

public class CollisionHandler
{
    public boolean handleCollision(Character character, Map map, PositionChange positionChange, GameWorld gameWorld)
    {
        int newRow = positionChange.getFinalRow();
        int newColumn = positionChange.getFinalColumn();

        if (newRow < 0 || newRow >= Map.NUM_ROWS || newColumn < 0 || newColumn >= Map.NUM_COLUMNS)
        {
            return true;
        }

        MapElement collisionTest = map.getElement(newRow, newColumn);
        if (collisionTest == null)
        {
            return false;
        }

        if (!collisionTest.interactsWithCharacter(character))
        {
            return true;
        }

        if (collisionTest instanceof RockWall)
        {
            ((RockWall)collisionTest).damage(character.getDamage());
            if (((RockWall)collisionTest).getHP() <= 0)
            {
                map.setElement(collisionTest.getRow(), collisionTest.getColumn(), null);
            }

            return true;
        }

        new CharacterInteracter().handleCharacterInteraction(character, (CharacterMapElement)collisionTest, map, gameWorld);

        return true;
    }
}
