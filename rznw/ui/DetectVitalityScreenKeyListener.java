package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.element.EnemyMapElement;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Vector;

public class DetectVitalityScreenKeyListener extends StateTransitionKeyListener
{
    private static int PAGE_SIZE = 20;

    private DetectVitalityScreenRenderer detectVitalityScreenRenderer;
    private GameWorld gameWorld;
    private MenuState state;
    private Vector<EnemyMapElement> visibleEnemies;

    public DetectVitalityScreenKeyListener(DetectVitalityScreenRenderer detectVitalityScreenRenderer, GameWorld gameWorld)
    {
        this.detectVitalityScreenRenderer = detectVitalityScreenRenderer;
        this.gameWorld = gameWorld;
    }

    public void keyPressed(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                if (this.state != null)
                {
                    this.state.moveUp();
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                if (this.state != null)
                {
                    this.state.moveDown();
                }
                break;
        }

        switch (this.state.getEntryNumber())
        {
            case 0:
                this.detectVitalityScreenRenderer.renderMap(this.gameWorld.getMap());
                break;
            case 1:
                this.detectVitalityScreenRenderer.renderNumberedMap(this.gameWorld.getMainCharacter(), this.gameWorld.getMap());
                break;
            default:
                EnemyMapElement[] visibleEnemiesArray = new EnemyMapElement[0];
                visibleEnemiesArray = this.visibleEnemies.toArray(visibleEnemiesArray);
                int startIndex = (this.state.getEntryNumber() - 2) * DetectVitalityScreenKeyListener.PAGE_SIZE;
                int exclusiveEndIndex = Math.min(startIndex + DetectVitalityScreenKeyListener.PAGE_SIZE, visibleEnemiesArray.length);
                EnemyMapElement[] activeVisibleEnemies = Arrays.copyOfRange(visibleEnemiesArray, startIndex, exclusiveEndIndex);
                this.detectVitalityScreenRenderer.renderEnemyHP(activeVisibleEnemies);
                break;
        }
    }

    public void enterState(int previousState)
    {
        this.visibleEnemies = this.getVisibleEnemies();
        int numEnemyPages = (int)Math.ceil((double)this.visibleEnemies.size() / DetectVitalityScreenKeyListener.PAGE_SIZE);
        System.out.println("Enemies and pages: " + visibleEnemies.size() + " - " + numEnemyPages);
        this.state = new MenuState(1 + numEnemyPages);

        this.detectVitalityScreenRenderer.renderMap(this.gameWorld.getMap());
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_DETECT_VITALITY;
    }

    private Vector<EnemyMapElement> getVisibleEnemies()
    {
        Vector<EnemyMapElement> enemies = new Vector<EnemyMapElement>();

        Map map = this.gameWorld.getMap();
        MainCharacter character = this.gameWorld.getMainCharacter();

        for (int i = 0; i < Map.NUM_ROWS; i++)
        {
            for (int j = 0; j < Map.NUM_COLUMNS; j++)
            {
                MapElement element = map.getElement(i, j);

                boolean visible = map.isVisible(i, j);
                int skillPoints = character.getSkillPoints(0);
                int radius = 1 + skillPoints;
                MapElement characterElement = character.getMapElement();
                double distance = Math.sqrt(Math.pow(characterElement.getRow() - i, 2) + Math.pow(characterElement.getColumn() - j, 2));

                if (element != null && element.isEnemy() && (visible || distance <= radius)) {
                    enemies.add((EnemyMapElement)element);
                }
            }
        }

        return enemies;
    }
}
