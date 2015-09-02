package rznw.turn;

import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;
import rznw.turn.positionchange.KeyBasedPositionChange;
import rznw.turn.positionchange.PositionChange;
import rznw.ui.CharacterSummaryRenderer;

public class MainCharacterTurnHandler
{
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
