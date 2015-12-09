package rznw.turn;

import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;

import rznw.game.Character;
import rznw.game.SummonedCharacter;
import rznw.game.SummonedZombie;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.KillBonusGranter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.Blotch;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.FireElement;
import rznw.map.element.MapElement;
import rznw.map.element.Stairs;
import rznw.map.element.SummonedZombieMapElement;
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
                    character.damage(20, null, this.gameWorld, Character.DAMAGE_SOURCE_OTHER);
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

    private void handleSummonedZombieTurns()
    {
        System.out.println("Handling summon turns");

        MainCharacter character = this.gameWorld.getMainCharacter();

        Collection<SummonedCharacter> summons = this.gameWorld.getMap().getSummons();
        for (Iterator iterator = summons.iterator(); iterator.hasNext();)
        {
            System.out.println("Have a summon");

            SummonedCharacter summon = (SummonedCharacter)iterator.next();
            EnemyAIBasedPositionChange summonPositionChange = summon.getPositionChange(this.gameWorld);
            System.out.println("Position change: " + summonPositionChange.getInitialRow() + ", " + summonPositionChange.getInitialColumn() + " -> " + summonPositionChange.getFinalRow() + ", " + summonPositionChange.getFinalColumn());

            this.handleCharacterTurn(summonPositionChange, summon);

            summon.getStatusEffects().processTurn(summon, this.gameWorld);
        }
    }

    public void handleEnemyTurns()
    {
        this.handleSummonedZombieTurns();

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

            Map map = this.gameWorld.getMap();
            MapElement enemyMapElement = enemy.getMapElement();
            MapElement backgroundElement = map.getBackgroundElement(enemyMapElement.getRow(), enemyMapElement.getColumn());
            if (backgroundElement instanceof Blotch)
            {
                System.out.println("Poisoning enemy from blotch");
                enemy.getStatusEffects().poison();
            }

            if (backgroundElement instanceof FireElement)
            {
                System.out.println("Enemy runs into fire element");
                int damage = 10 * character.getSpellPoints(4);
                System.out.println("Enemy HP before: " + enemy.getHP());
                enemy.damage(damage, character, this.gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
                System.out.println("Enemy HP after: " + enemy.getHP());
            }

            enemy.getStatusEffects().processTurn(enemy, this.gameWorld);
        }

        if (character.getStatusEffects().isSkippingTurn())
        {
            System.out.println("Enemies take a turn while you are sleeping");
            character.getStatusEffects().processTurn(character, gameWorld);
            this.handleEnemyTurns();
        }
    }

    public void handlePostEnemyTurns()
    {
        Map map = this.gameWorld.getMap();
        MainCharacter character = this.gameWorld.getMainCharacter();

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

                        if (character.getStatusEffects().isInferringZombie())
                        {
                            character.getStatusEffects().disableInferZombie();

                            System.out.println("Inferring zombie at: " + element.getRow() + ", " + element.getColumn());

                            int maxHP = 200 + 10 * character.getSpellPoints(13);
                            System.out.println("Max HP is: " + maxHP);

                            SummonedZombie zombie = new SummonedZombie(maxHP);
                            SummonedZombieMapElement zombieElement = new SummonedZombieMapElement(element.getRow(), element.getColumn(), zombie);
                            zombie.setMapElement(zombieElement);
                            gameWorld.getMap().setElement(zombieElement.getRow(), zombieElement.getColumn(), zombieElement);
                        }
                    }
                }

                if (element instanceof SummonedZombieMapElement)
                {
                    Character zombie = ((SummonedZombieMapElement)element).getCharacter();
                    if (zombie.isDead())
                    {
                        map.setElement(element.getRow(), element.getColumn(), null);
                    }
                }

                element = map.getBackgroundElement(row, column);
                if (element instanceof FireElement)
                {
                    FireElement fireElement = (FireElement)element;
                    fireElement.decreaseDuration();

                    if (fireElement.isExpired())
                    {
                        MapElement previousMapElement = fireElement.getPreviousMapElement();
                        map.setBackgroundElement(fireElement.getRow(), fireElement.getColumn(), previousMapElement);
                    }
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
