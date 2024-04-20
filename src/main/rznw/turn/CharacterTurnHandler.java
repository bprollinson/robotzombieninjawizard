package rznw.turn;

import rznw.game.Character;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapElementSetter;
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

        MapElement mapElement = character.getMapElement();
        MapElementSetter.setElement(map, mapElement, positionChange.getFinalRow(), positionChange.getFinalColumn());
    }
}
