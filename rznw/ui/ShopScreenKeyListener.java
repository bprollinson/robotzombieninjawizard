package rznw.ui;

import rznw.game.maincharacter.inventory.RandomInventoryGenerator;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class ShopScreenKeyListener extends StateTransitionKeyListener
{
    private ShopScreenRenderer shopScreenRenderer;
    private GameWorld gameWorld;
    private MenuState state;

    public ShopScreenKeyListener(ShopScreenRenderer shopScreenRenderer, GameWorld gameWorld)
    {
        this.shopScreenRenderer = shopScreenRenderer;
        this.gameWorld = gameWorld;
        this.state = new MenuState(5);
    }

    public void enterState(int previousState)
    {
        this.shopScreenRenderer.renderTopMenu(gameWorld.getMainCharacter(), this.state);

        RandomInventoryGenerator.handleRegeneration(gameWorld);
    }

    public void keyPressed(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                this.state.moveUp();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                this.state.moveDown();
                break;
        }

        this.shopScreenRenderer.renderTopMenu(gameWorld.getMainCharacter(), this.state);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ENTER)
        {
            switch (this.state.getEntryNumber())
            {
                case 0:
                    return DispatchKeyListener.STATE_BUY_ITEMS_MENU;
                case 1:
                    return DispatchKeyListener.STATE_BUY_EQUIPMENT_MENU;
                case 2:
                    return DispatchKeyListener.STATE_SELL_ITEMS_MENU;
                case 3:
                    return DispatchKeyListener.STATE_SELL_EQUIPMENT_MENU;
                case 4:
                    return DispatchKeyListener.STATE_GAME_MOTION;
            }
        }

        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_SHOP;
    }
}
