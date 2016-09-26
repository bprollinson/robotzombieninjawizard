package rznw.turn;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

public class PostCharacterTurnHandler implements TurnFragmentHandler
{
    private GameWorld gameWorld;

    public PostCharacterTurnHandler(GameWorld gameWorld)
    {
        this.gameWorld = gameWorld;
    }

    public void handleTurnFragment()
    {
        Map map = this.gameWorld.getMap();
        MainCharacter character = this.gameWorld.getMainCharacter();
        MapElement characterMapElement = character.getMapElement();

        map.setElementVisited((MainCharacter)character, characterMapElement.getRow(), characterMapElement.getColumn());

        MapElement backgroundElement = map.getBackgroundElement(characterMapElement.getRow(), characterMapElement.getColumn());
        if (backgroundElement != null)
        {
            backgroundElement.collideWithMainCharacter(this.gameWorld, character);
        }

        if (!character.isDead())
        {
            this.handleMainCharacterRegeneration();
        }

        new EnemyClearer().clearEnemies(this.gameWorld, map, character);
    }

    private void handleMainCharacterRegeneration()
    {
        MapElement mapElement = this.gameWorld.getMainCharacter().getMapElement();
        int row = mapElement.getRow();
        int column = mapElement.getColumn();

        Map map = this.gameWorld.getMap();
        if (!map.elementVisited(row, column))
        {
            map.visit(row, column);

            MainCharacter mainCharacter = this.gameWorld.getMainCharacter();
            mainCharacter.getSteps().incrementSteps();
        }
    }
}
