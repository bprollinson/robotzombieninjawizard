package rznw.turn;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.Stairs;
import rznw.turn.positionchange.KeyBasedPositionChange;
import rznw.turn.positionchange.PositionChange;
import rznw.turn.positionchange.RandomPositionChange;
import rznw.ui.CharacterSummaryRenderer;
import rznw.ui.LogRendererFactory;

import java.awt.event.KeyEvent;

public class MainCharacterTurnHandler
{
    private static int KEY_V = 86;
    private static int KEY_5 = 101;
    private static int KEY_5_NO_NUM_LOCK = 65368;
    private static int KEY_5_NO_NUM_LOCK_ALTERNATE = 12;

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
            this.gameWorld.getMainCharacter().getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_REGENERATE_SHOP, true);
            this.gameWorld.generateNextMap();
            Map map = this.gameWorld.getMap();
            MapElement characterElement = this.gameWorld.getMainCharacter().getMapElement();
            map.setElementVisited(this.gameWorld.getMainCharacter(), characterElement.getRow(), characterElement.getColumn());
            this.renderer.render(this.gameWorld);

            return;
        }

        MainCharacter character = this.gameWorld.getMainCharacter();
        PositionChange characterPositionChange = new KeyBasedPositionChange(character, event);

        if (event.getKeyCode() == MainCharacterTurnHandler.KEY_5 || event.getKeyCode() == MainCharacterTurnHandler.KEY_5_NO_NUM_LOCK || event.getKeyCode() == MainCharacterTurnHandler.KEY_5_NO_NUM_LOCK_ALTERNATE)
        {
            this.handlePostTurn();
            this.renderer.render(this.gameWorld);
            return;
        }

        if (!characterPositionChange.isChange())
        {
            return;
        }

        if (character.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_CONFUSION) > 0)
        {
            LogRendererFactory.instance().log("You are confused.");

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
        new PostEnemyTurnHandler(this.gameWorld).handleTurnFragment();
    }

    private boolean eventIsFloorChange(KeyEvent event)
    {
        MapElement mapElement = this.gameWorld.getMainCharacter().getMapElement();

        int row = mapElement.getRow();
        int column = mapElement.getColumn();

        Map map = this.gameWorld.getMap();

        return event.isShiftDown() && event.getKeyCode() == MainCharacterTurnHandler.KEY_V && map.getBackgroundElement(row, column) instanceof Stairs;
    }

    public void renderSummary()
    {
        this.renderer.render(this.gameWorld);
    }
}
