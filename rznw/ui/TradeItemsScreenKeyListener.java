package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Bomb;
import rznw.game.maincharacter.inventory.FullManaPotion;
import rznw.game.maincharacter.inventory.FullPotion;
import rznw.game.maincharacter.inventory.Herb;
import rznw.game.maincharacter.inventory.InventoryFullException;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.ManaPotion;
import rznw.game.maincharacter.inventory.MegaBomb;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.maincharacter.inventory.ReplenishingFullManaPotion;
import rznw.game.maincharacter.inventory.ReplenishingFullPotion;
import rznw.game.maincharacter.inventory.ReplenishingHerb;
import rznw.game.maincharacter.inventory.ReplenishingSanityDrop;
import rznw.game.maincharacter.inventory.ReplenishingXRayDrop;
import rznw.game.maincharacter.inventory.SanityDrop;
import rznw.game.maincharacter.inventory.XRayDrop;
import rznw.map.GameWorld;
import rznw.turn.MainCharacterTurnHandler;
import rznw.utility.RandomNumberGenerator;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class TradeItemsScreenKeyListener extends StateTransitionKeyListener
{
    private TradeItemsScreenRenderer tradeItemsScreenRenderer;
    private GameWorld gameWorld;
    private MainCharacterTurnHandler turnHandler;
    private MenuState state;
    private HashMap<Integer, Integer> selectionMap;
    private int numTradedIn;

    public TradeItemsScreenKeyListener(TradeItemsScreenRenderer tradeItemsScreenRenderer, GameWorld gameWorld, MainCharacterTurnHandler turnHandler)
    {
        this.tradeItemsScreenRenderer = tradeItemsScreenRenderer;
        this.gameWorld = gameWorld;
        this.turnHandler = turnHandler;
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
            case KeyEvent.VK_ENTER:
                int numLeft = this.gameWorld.getMainCharacter().getInventory().getItemGroup(this.state.getEntryNumber()).getNumItems() - this.selectionMap.get(this.state.getEntryNumber());
                if (numLeft > 0)
                {
                    int numUsed = this.selectionMap.get(this.state.getEntryNumber());
                    numUsed++;
                    this.selectionMap.put(this.state.getEntryNumber(), numUsed);

                    this.numTradedIn++;
                }
                break;
        }

        this.tradeItemsScreenRenderer.render(this.state, this.gameWorld.getMainCharacter(), this.selectionMap);
    }

    public void enterState(int previousState)
    {
        MainCharacter character = gameWorld.getMainCharacter();
        this.state = new MenuState(character.getInventory().getNumItemGroups() - 1);

        this.selectionMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < character.getInventory().getNumItemGroups(); i++)
        {
            this.selectionMap.put(i, 0);
        }

        this.numTradedIn = 0;

        this.tradeItemsScreenRenderer.render(this.state, this.gameWorld.getMainCharacter(), this.selectionMap);
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

        int skillPoints = this.gameWorld.getMainCharacter().getSkillPoints(MainCharacter.SKILL_ITEM_TRADE);
        int numToTradeIn = 6 - (int)Math.floor(skillPoints / 5);

        if (this.numTradedIn == numToTradeIn)
        {
            System.out.println("Done trading in!");

            this.assignRandomItem();
            this.removeSelectedItems();

            this.turnHandler.handlePostTurn();

            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_TRADE_ITEMS;
    }

    private void assignRandomItem()
    {
        InventoryItem[] possibleItems = new InventoryItem[] {
            new Bomb(),
            new FullManaPotion(),
            new FullPotion(),
            new Herb(),
            new ManaPotion(),
            new MegaBomb(),
            new Potion(),
            new ReplenishingFullManaPotion(),
            new ReplenishingFullPotion(),
            new ReplenishingHerb(),
            new ReplenishingSanityDrop(),
            new ReplenishingXRayDrop(),
            new SanityDrop(),
            new XRayDrop()
        };

        int index = RandomNumberGenerator.randomInteger(0, possibleItems.length - 1);
        InventoryItem item = possibleItems[index];

        System.out.println("Generated item is: " + item.getDisplayName());

        try
        {
            this.gameWorld.getMainCharacter().getInventory().addItems(new InventoryItemGroup(item, 1));
        }
        catch (InventoryFullException ife)
        {
            System.out.println("Inventory full");
        }
    }

    private void removeSelectedItems()
    {
        for (int i = this.selectionMap.size() - 1; i >= 0; i--)
        {
            int numToRemove = this.selectionMap.get(i);
            InventoryItem item = this.gameWorld.getMainCharacter().getInventory().getItemGroup(i).getItem();
            this.gameWorld.getMainCharacter().getInventory().removeItems(new InventoryItemGroup(item, numToRemove));
        }
    }
}
