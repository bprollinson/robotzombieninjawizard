package rznw.turn;

import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
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

    private Map map;
    private MainCharacter character;
    private CharacterSummaryRenderer renderer;

    public MainCharacterTurnHandler(Map map, MainCharacter character, CharacterSummaryRenderer renderer)
    {
        this.map = map;
        this.character = character;
        this.renderer = renderer;
    }

    public void handleTurn(KeyEvent event)
    {
        if (this.eventIsFloorChange(event))
        {
            System.out.println("Going down to the next floor");
            return;
        }

        KeyBasedPositionChange characterPositionChange = new KeyBasedPositionChange(this.character, event);
        this.handleCharacterTurn(characterPositionChange, this.character);

        Collection<EnemyCharacter> enemies = this.map.getEnemies();
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            EnemyAIBasedPositionChange enemyPositionChange = enemy.getPositionChange(this.character);

            this.handleCharacterTurn(enemyPositionChange, enemy);
        }

        this.renderer.render(this.character);
    }

    private boolean eventIsFloorChange(KeyEvent event)
    {
        MapElement mapElement = this.character.getMapElement();

        int row = mapElement.getRow();
        int column = mapElement.getColumn();

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

        CollisionHandler collisionHandler = new CollisionHandler();
        boolean collided = collisionHandler.handleCollision(character, this.map, positionChange);
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
