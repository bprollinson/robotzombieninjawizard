package rznw.turn;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.Stairs;
import rznw.turn.positionchange.KeyBasedPositionChange;
import rznw.turn.positionchange.PositionChange;
import rznw.turn.positionchange.RandomPositionChange;
import rznw.ui.CharacterSummaryRenderer;
import rznw.utility.RandomNumberGenerator;

import java.awt.event.KeyEvent;

public class MainCharacterTurnHandler
{
    private static int KEY_V = 86;
    private static int KEY_5 = 101;

    private GameWorld gameWorld;
    private CharacterSummaryRenderer renderer;

    public MainCharacterTurnHandler(GameWorld gameWorld, CharacterSummaryRenderer renderer)
    {
        this.gameWorld = gameWorld;
        this.renderer = renderer;
    }

    public void handleTurn(KeyEvent event)
    {
        if (this.eventIsFloorChange(event))
        {
            System.out.println("Going down to the next floor");
            this.gameWorld.getMainCharacter().getStatusEffects().enableRegenerateShop();
            this.gameWorld.generateNextMap();
            Map map = this.gameWorld.getMap();
            MapElement characterElement = this.gameWorld.getMainCharacter().getMapElement();
            map.setElementVisited(this.gameWorld.getMainCharacter(), characterElement.getRow(), characterElement.getColumn());
            this.renderer.render(this.gameWorld);

            return;
        }

        MainCharacter character = this.gameWorld.getMainCharacter();
        PositionChange characterPositionChange = new KeyBasedPositionChange(character, event);

        if (event.getKeyCode() == MainCharacterTurnHandler.KEY_5)
        {
            this.handlePostTurn();
            this.renderer.render(this.gameWorld);
            return;
        }

        if (!characterPositionChange.isChange())
        {
            return;
        }

        if (character.getStatusEffects().isConfused())
        {
            System.out.println("Main character is confused!");

            characterPositionChange = new RandomPositionChange(character);
        }

        new CharacterTurnHandler(this.gameWorld).handleTurnFragment(characterPositionChange, character);

        this.handlePostTurn();

        this.renderer.render(this.gameWorld);
    }

    public void handlePostTurn()
    {
        new PostCharacterTurnHandler(this.gameWorld).handleTurnFragment();
        new EnemyTurnHandler(this.gameWorld).handleTurnFragment();
        this.handlePostEnemyTurns();
    }

    private boolean eventIsFloorChange(KeyEvent event)
    {
        MapElement mapElement = this.gameWorld.getMainCharacter().getMapElement();

        int row = mapElement.getRow();
        int column = mapElement.getColumn();

        Map map = this.gameWorld.getMap();

        return event.isShiftDown() && event.getKeyCode() == MainCharacterTurnHandler.KEY_V && map.getBackgroundElement(row, column) instanceof Stairs;
    }

    private void handleMainCharacterRevival()
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        int revivalProbability = 5 * character.getStatPoints(MainCharacter.STAT_LAST_BREATH);
        System.out.println("Revival probability: " + revivalProbability);

        if (RandomNumberGenerator.rollSucceeds(revivalProbability))
        {
            System.out.println("Reviving");
            character.setHP(1);
        }
    }

    private void handlePostEnemyTurns()
    {
        Map map = this.gameWorld.getMap();
        MainCharacter character = this.gameWorld.getMainCharacter();

        if (character.isDead())
        {
            this.handleMainCharacterRevival();
        }

        new EnemyClearer().clearEnemies(this.gameWorld, map, character);

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);
                if (element != null)
                {
                    element.processTurn(map);
                }

                element = map.getBackgroundElement(row, column);
                if (element != null)
                {
                    element.processTurn(map);
                }
            }
        }

        character.getStatusEffects().processTurn(character, this.gameWorld);
    }

    public void renderSummary()
    {
        this.renderer.render(this.gameWorld);
    }
}
