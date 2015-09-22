import rznw.game.CharacterGenerator;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.generator.MapGenerator;
import rznw.turn.MainCharacterTurnHandler;
import rznw.ui.CharacterScreenKeyListener;
import rznw.ui.CharacterScreenRenderer;
import rznw.ui.CharacterSummaryRenderer;
import rznw.ui.DispatchKeyListener;
import rznw.ui.InventoryScreenKeyListener;
import rznw.ui.InventoryScreenRenderer;
import rznw.ui.LoadScreenKeyListener;
import rznw.ui.LoadScreenRenderer;
import rznw.ui.MainGameFrame;
import rznw.ui.MainMenuKeyListener;
import rznw.ui.MainMenuRenderer;
import rznw.ui.MapRenderer;
import rznw.ui.MovementKeyListener;
import rznw.ui.SaveScreenKeyListener;
import rznw.ui.SaveScreenRenderer;
import rznw.ui.SkillsScreenKeyListener;
import rznw.ui.SkillsScreenRenderer;
import rznw.ui.SpellsScreenKeyListener;
import rznw.ui.SpellsScreenRenderer;

public class Test
{
    public static void main(String[] args)
    {
        CharacterGenerator characterGenerator = new CharacterGenerator();
        MainCharacter character = characterGenerator.generateMainCharacter();

        MainGameFrame frame = new MainGameFrame("Robot Zombie Ninja Wizard");

        MapGenerator mapGenerator = new MapGenerator();
        GameWorld gameWorld = new GameWorld(character, characterGenerator, mapGenerator);
        gameWorld.generateMap();

        MapRenderer renderer = new MapRenderer(frame);
        renderer.render(gameWorld.getMap());

        CharacterSummaryRenderer characterSummaryRenderer = new CharacterSummaryRenderer(frame);
        characterSummaryRenderer.render(character);

        MainCharacterTurnHandler turnHandler = new MainCharacterTurnHandler(gameWorld, character, characterSummaryRenderer);

        MovementKeyListener movementListener = new MovementKeyListener(turnHandler, renderer, gameWorld);
        MainMenuKeyListener mainMenuKeyListener = new MainMenuKeyListener(new MainMenuRenderer(frame));
        CharacterScreenKeyListener characterScreenKeyListener = new CharacterScreenKeyListener(new CharacterScreenRenderer(frame));
        SkillsScreenKeyListener skillsScreenKeyListener = new SkillsScreenKeyListener(new SkillsScreenRenderer(frame));
        SpellsScreenKeyListener spellsScreenKeyListener = new SpellsScreenKeyListener(new SpellsScreenRenderer(frame));
        InventoryScreenKeyListener inventoryScreenKeyListener = new InventoryScreenKeyListener(new InventoryScreenRenderer(frame));
        SaveScreenKeyListener saveScreenKeyListener = new SaveScreenKeyListener(new SaveScreenRenderer(frame));
        LoadScreenKeyListener loadScreenKeyListener = new LoadScreenKeyListener(new LoadScreenRenderer(frame));
        DispatchKeyListener dispatchListener = new DispatchKeyListener(movementListener, mainMenuKeyListener, characterScreenKeyListener, skillsScreenKeyListener, spellsScreenKeyListener, inventoryScreenKeyListener, saveScreenKeyListener, loadScreenKeyListener);

        frame.display(dispatchListener);
    }
}
