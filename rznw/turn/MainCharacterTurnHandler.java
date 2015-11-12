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
import rznw.map.element.TrapMapElement;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;
import rznw.turn.positionchange.KeyBasedPositionChange;
import rznw.turn.positionchange.PositionChange;
import rznw.ui.CharacterSummaryRenderer;
import rznw.utility.RandomNumberGenerator;

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
            Map map = this.gameWorld.getMap();
            MapElement characterElement = this.gameWorld.getMainCharacter().getMapElement();
            map.setElementVisited(this.gameWorld.getMainCharacter(), characterElement.getRow(), characterElement.getColumn());
            this.renderer.render(this.gameWorld);

            return;
        }

        MainCharacter character = this.gameWorld.getMainCharacter();
        KeyBasedPositionChange characterPositionChange = new KeyBasedPositionChange(character, event);
        this.handleCharacterTurn(characterPositionChange, character);

        this.handleTrapCollision();

        if (!character.isDead())
        {
            this.handleMainCharacterRegeneration();
        }

        this.handleEnemyTurns();

        if (character.isDead())
        {
            this.handleMainCharacterRevival();
        }

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
            map.setElementVisited((MainCharacter)character, newRow, newColumn);
        }
    }

    private void handleTrapCollision()
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        MapElement characterMapElement = character.getMapElement();

        Map map = this.gameWorld.getMap();
        MapElement trap = map.getBackgroundElement(characterMapElement.getRow(), characterMapElement.getColumn());
        if (trap instanceof TrapMapElement)
        {
            System.out.println("It's a trap!");
            character.damage(20);
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

    private void handleMainCharacterRevival()
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        int revivalProbability = 5 * character.getStatPoints(2);
        System.out.println("Revival probability: " + revivalProbability);

        int randomNumber = RandomNumberGenerator.randomInteger(1, 100);
        if (randomNumber <= revivalProbability)
        {
            System.out.println("Reviving");
            character.setHP(1);
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
