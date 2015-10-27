package rznw.turn;

import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.Stairs;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;
import rznw.turn.positionchange.KeyBasedPositionChange;
import rznw.turn.positionchange.PositionChange;
import rznw.ui.CharacterSummaryRenderer;

public class MainCharacterTurnHandler
{
    private static int KEY_V = 86;

    private GameWorld gameWorld;
    private CharacterSummaryRenderer renderer;

    public MainCharacterTurnHandler(GameWorld gameWorld, CharacterSummaryRenderer renderer)
    {
        this.gameWorld = gameWorld;
        this.renderer = renderer;
    }

    public void handleTurn(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return;
        }

        if (this.eventIsFloorChange(event))
        {
            System.out.println("Going down to the next floor");
            this.gameWorld.generateNextMap();
            this.renderer.render(this.gameWorld);

            return;
        }

        MainCharacter character = this.gameWorld.getMainCharacter();
        KeyBasedPositionChange characterPositionChange = new KeyBasedPositionChange(character, event);
        this.handleCharacterTurn(characterPositionChange, character);

        if (!character.isDead())
        {
            this.handleMainCharacterRegeneration();
        }

        this.handleEnemyTurns();

        this.renderer.render(this.gameWorld);
    }

    private boolean eventIsFloorChange(KeyEvent event)
    {
        MapElement mapElement = this.gameWorld.getMainCharacter().getMapElement();

        int row = mapElement.getRow();
        int column = mapElement.getColumn();

        Map map = this.gameWorld.getMap();

        if (event.isShiftDown() && event.getKeyCode() == MainCharacterTurnHandler.KEY_V && map.getBackgroundElement(row, column) instanceof Stairs)
        {
            return true;
        }

        return false;
    }

    private void handleCharacterTurn(PositionChange positionChange, Character character)
    {
        if (!positionChange.isChange())
        {
            return;
        }

        Map map = this.gameWorld.getMap();

        CollisionHandler collisionHandler = new CollisionHandler();
        boolean collided = collisionHandler.handleCollision(character, map, positionChange);
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

        if (character instanceof MainCharacter)
        {
            map.setElementVisited(newRow, newColumn);
        }
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
            mainCharacter.incrementSteps();
        }
    }

    public void handleEnemyTurns()
    {
        MainCharacter character = this.gameWorld.getMainCharacter();

        Collection<EnemyCharacter> enemies = this.gameWorld.getMap().getEnemies();
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            EnemyAIBasedPositionChange enemyPositionChange = enemy.getPositionChange(character);

            this.handleCharacterTurn(enemyPositionChange, enemy);
        }
    }

    public void renderSummary()
    {
        this.renderer.render(this.gameWorld);
    }
}
