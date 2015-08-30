package rznw.turn;

import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;
import rznw.turn.positionchange.KeyBasedPositionChange;

public class MainCharacterTurnHandler
{
    private Map map;
    private MainCharacter character;

    public MainCharacterTurnHandler(Map map, MainCharacter character)
    {
        this.map = map;
        this.character = character;
    }

    public void handleTurn(KeyEvent event)
    {
        this.handleMainCharacterTurn(event);

        Collection<EnemyCharacter> enemies = this.map.getEnemies();
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            this.handleEnemyTurn(enemy);
        }
    }

    private void handleMainCharacterTurn(KeyEvent event)
    {
        KeyBasedPositionChange positionChange = new KeyBasedPositionChange(this.character, event);
        if (!positionChange.isChange())
        {
            return;
        }

        int newRow = positionChange.getFinalRow();
        int newColumn = positionChange.getFinalColumn();

        MapElement collisionTest = map.getElement(newRow, newColumn);
        if (collisionTest != null)
        {
            return;
        }

        map.setElement(positionChange.getInitialRow(), positionChange.getInitialColumn(), null);

        MapElement mapElement = character.getMapElement();
        map.setElement(newRow, newColumn, mapElement);
        mapElement.setRow(newRow);
        mapElement.setColumn(newColumn);
    }

    private void handleEnemyTurn(EnemyCharacter enemy)
    {
        EnemyAIBasedPositionChange positionChange = enemy.getPositionChange(this.character);
        if (!positionChange.isChange())
        {
            return;
        }

        int newRow = positionChange.getFinalRow();
        int newColumn = positionChange.getFinalColumn();

        MapElement collisionTest = map.getElement(newRow, newColumn);
        if (collisionTest != null)
        {
            return;
        }

        map.setElement(positionChange.getInitialRow(), positionChange.getInitialColumn(), null);

        MapElement mapElement = enemy.getMapElement();
        map.setElement(newRow, newColumn, mapElement);
        mapElement.setRow(newRow);
        mapElement.setColumn(newColumn);
    }
}
