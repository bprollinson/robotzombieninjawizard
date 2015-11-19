package rznw.turn;

import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.KillBonusGranter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
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
    private KillBonusGranter killBonusGranter;

    public MainCharacterTurnHandler(GameWorld gameWorld, CharacterSummaryRenderer renderer)
    {
        this.gameWorld = gameWorld;
        this.renderer = renderer;
        this.killBonusGranter = new KillBonusGranter();
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

        this.handlePostCharacterTurn();
        this.handleEnemyTurns();
        this.handlePostEnemyTurns();

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
    }

    public void handlePostCharacterTurn()
    {
        Map map = this.gameWorld.getMap();
        Character character = this.gameWorld.getMainCharacter();
        MapElement characterMapElement = character.getMapElement();

        map.setElementVisited((MainCharacter)character, characterMapElement.getRow(), characterMapElement.getColumn());

        this.handleTrapCollision();

        if (!character.isDead())
        {
            this.handleMainCharacterRegeneration();
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
            TrapMapElement properTrap = (TrapMapElement)trap;

            if (!properTrap.isSprung())
            {
                properTrap.spring();

                int disarmProbability = 5 * character.getSkillPoints(11);

                if (RandomNumberGenerator.rollSucceeds(disarmProbability))
                {
                    System.out.println("Disarmed trap");
                }
                else
                {
                    System.out.println("It's a trap!");
                    character.damage(20);
                }
            }
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

        if (RandomNumberGenerator.rollSucceeds(revivalProbability))
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
            if (enemy.getStatusEffects().isFrozen())
            {
                System.out.println("Enemy is frozen!");
            }
            else
            {
                EnemyAIBasedPositionChange enemyPositionChange = enemy.getPositionChange(character);

                this.handleCharacterTurn(enemyPositionChange, enemy);
            }

            enemy.getStatusEffects().processTurn();
        }
    }

    public void handlePostEnemyTurns()
    {
        Map map = this.gameWorld.getMap();
        Character character = this.gameWorld.getMainCharacter();

        if (character.isDead())
        {
            this.handleMainCharacterRevival();
        }

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);
                if (element instanceof EnemyMapElement)
                {
                    Character enemy = ((EnemyMapElement)element).getCharacter();
                    if (enemy.isDead())
                    {
                        this.killBonusGranter.grantKillBonuses(character, enemy);
                        map.setElement(element.getRow(), element.getColumn(), null);
                    }
                }
            }
        }
    }

    public void renderSummary()
    {
        this.renderer.render(this.gameWorld);
    }
}
