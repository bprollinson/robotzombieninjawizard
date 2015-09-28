import rznw.game.CharacterGenerator;
import rznw.map.GameWorld;
import rznw.map.generator.MapGenerator;
import rznw.turn.MainCharacterTurnHandler;
import rznw.ui.CharacterSummaryRenderer;
import rznw.ui.DispatchKeyListener;
import rznw.ui.MainGameFrame;
import rznw.ui.StartScreenRenderer;
import rznw.ui.StateTransitionKeyListenerContainer;

public class Test
{
    public static void main(String[] args)
    {
        MainGameFrame frame = new MainGameFrame("Robot Zombie Ninja Wizard");

        CharacterGenerator characterGenerator = new CharacterGenerator();

        MapGenerator mapGenerator = new MapGenerator();
        GameWorld gameWorld = new GameWorld(characterGenerator, mapGenerator);
        gameWorld.initializeToDefaultState();

        StartScreenRenderer startScreenRenderer = new StartScreenRenderer(frame);
        startScreenRenderer.render();

        CharacterSummaryRenderer characterSummaryRenderer = new CharacterSummaryRenderer(frame);
        MainCharacterTurnHandler turnHandler = new MainCharacterTurnHandler(gameWorld, characterSummaryRenderer);

        StateTransitionKeyListenerContainer listenerContainer = new StateTransitionKeyListenerContainer(frame, turnHandler, gameWorld, startScreenRenderer);
        DispatchKeyListener dispatchListener = new DispatchKeyListener(listenerContainer);

        frame.display(dispatchListener);
    }
}
