package rznw.turn;

import rznw.game.Character;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.PositionChange;

public class CharacterTurnHandler
{
    private GameWorld gameWorld;

    public CharacterTurnHandler(GameWorld gameWorld)
    {
        this.gameWorld = gameWorld;
    }

    public void handleTurnFragment(PositionChange positionChange, Character character)
    {
        if (!positionChange.isChange())
        {
            return;
        }

        Map map = this.gameWorld.getMap();

        CollisionHandler collisionHandler = new CollisionHandler();
        boolean collided = collisionHandler.handleCollision(character, map, positionChange, this.gameWorld);
        if (collided)
        {
            return;
        }

        map.setElement(positionChange.getInitialRow(), positionChange.getInitialColumn(), null);

        int newRow = positionChange.getFinalRow();
        int newColumn = positionChange.getFinalColumn();

        MapElement mapElement = character.getMapElement();
        map.setElement(newRow, newColumn, mapElement);
        mapElement.setRow(newRow);
        mapElement.setColumn(newColumn);
    }
}
