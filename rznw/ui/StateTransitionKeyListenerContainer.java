package rznw.ui;

import rznw.map.GameWorld;
import rznw.turn.MainCharacterTurnHandler;
import rznw.ui.MapRenderer;

import java.util.HashMap;

public class StateTransitionKeyListenerContainer
{
    private MainCharacterTurnHandler turnHandler;
    private MapRenderer renderer;
    private GameWorld gameWorld;

    private HashMap<Integer, StateTransitionKeyListener> listeners;

    public StateTransitionKeyListenerContainer(MainGameFrame frame, MainCharacterTurnHandler turnHandler, GameWorld gameWorld)
    {
        this.listeners = new HashMap<Integer, StateTransitionKeyListener>();

        this.listeners.put(DispatchKeyListener.STATE_START_SCREEN, new StartScreenKeyListener(new StartScreenRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_GAME_MOTION, new MovementKeyListener(turnHandler, new MapRenderer(frame), gameWorld));
        this.listeners.put(DispatchKeyListener.STATE_GAME_ESCAPE_MENU, new MainMenuKeyListener(new MainMenuRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_CHARACTER_SCREEN, new CharacterScreenKeyListener(new CharacterScreenRenderer(frame, gameWorld)));
        this.listeners.put(DispatchKeyListener.STATE_SKILLS_SCREEN, new SkillsScreenKeyListener(new SkillsScreenRenderer(frame), gameWorld, turnHandler));
        this.listeners.put(DispatchKeyListener.STATE_SPELLS_SCREEN, new SpellsScreenKeyListener(new SpellsScreenRenderer(frame), gameWorld, new MapRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_INVENTORY_SCREEN, new InventoryScreenKeyListener(new InventoryScreenRenderer(frame), gameWorld, turnHandler));
        this.listeners.put(DispatchKeyListener.STATE_SAVE_SCREEN, new SaveScreenKeyListener(new SaveScreenRenderer(frame), gameWorld));
        this.listeners.put(DispatchKeyListener.STATE_LOAD_SCREEN, new LoadScreenKeyListener(new LoadScreenRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_NEW_GAME_SCREEN, new NewGameScreenKeyListener(new NewGameScreenRenderer(frame), gameWorld));
        this.listeners.put(DispatchKeyListener.STATE_EXIT_SCREEN, new ExitScreenKeyListener(new ExitScreenRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_LEVEL_UP_STATS_MENU, new LevelUpStatsMenuKeyListener(new LevelUpStatsMenuRenderer(frame), gameWorld));
        this.listeners.put(DispatchKeyListener.STATE_LEVEL_UP_SKILLS_MENU, new LevelUpSkillsMenuKeyListener(new LevelUpSkillsMenuRenderer(frame), gameWorld));
        this.listeners.put(DispatchKeyListener.STATE_LEVEL_UP_SPELLS_MENU, new LevelUpSpellsMenuKeyListener(new LevelUpSpellsMenuRenderer(frame), gameWorld));
        this.listeners.put(DispatchKeyListener.STATE_DEATH_SCREEN, new DeathScreenKeyListener(new DeathScreenRenderer(frame), gameWorld));
        this.listeners.put(DispatchKeyListener.STATE_EQUIPMENT_SCREEN, new EquipmentScreenKeyListener(new EquipmentScreenRenderer(frame), gameWorld));
        this.listeners.put(DispatchKeyListener.STATE_WEAPONS_SCREEN, new WeaponsScreenKeyListener(new WeaponsScreenRenderer(frame), gameWorld));
        this.listeners.put(DispatchKeyListener.STATE_SHIELDS_SCREEN, new ShieldsScreenKeyListener(new ShieldsScreenRenderer(frame), gameWorld));
        this.listeners.put(DispatchKeyListener.STATE_ARMOR_SCREEN, new ArmorScreenKeyListener(new ArmorScreenRenderer(frame), gameWorld));
        this.listeners.put(DispatchKeyListener.STATE_DETECT_VITALITY, new DetectVitalityScreenKeyListener(new DetectVitalityScreenRenderer(frame), gameWorld));
        this.listeners.put(DispatchKeyListener.STATE_TRADE_ITEMS, new TradeItemsScreenKeyListener(new TradeItemsScreenRenderer(frame), gameWorld, turnHandler));
        this.listeners.put(DispatchKeyListener.STATE_SHOP, new ShopScreenKeyListener(new ShopScreenRenderer(frame), gameWorld, turnHandler));
        this.listeners.put(DispatchKeyListener.STATE_INSTRUCTIONS_SCREEN, new InstructionsScreenKeyListener(new InstructionsScreenRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_GAME_COMPLETED, new GameCompletedScreenKeyListener(new GameCompletedScreenRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_SAVE_CONFIRMATION_SCREEN, new SaveConfirmationScreenKeyListener(new SaveConfirmationScreenRenderer(frame)));
    }

    public StateTransitionKeyListener getListener(int state)
    {
        return this.listeners.get(state);
    }
}
