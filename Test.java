import rznw.game.CharacterGenerator;
import rznw.map.GameWorld;
import rznw.map.generator.MapGenerator;
import rznw.turn.MainCharacterTurnHandler;
import rznw.ui.CharacterSummaryRenderer;
import rznw.ui.DispatchKeyListener;
import rznw.ui.MainGameFrame;
import rznw.ui.MapRenderer;
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

        MapRenderer renderer = new MapRenderer(frame);
        renderer.render(gameWorld.getMap());

        CharacterSummaryRenderer characterSummaryRenderer = new CharacterSummaryRenderer(frame);
        characterSummaryRenderer.render(gameWorld);

        MainCharacterTurnHandler turnHandler = new MainCharacterTurnHandler(gameWorld, characterSummaryRenderer);

        StateTransitionKeyListenerContainer listenerContainer = new StateTransitionKeyListenerContainer(frame, turnHandler, renderer, gameWorld);
        DispatchKeyListener dispatchListener = new DispatchKeyListener(listenerContainer);

        frame.display(dispatchListener);
    }
}
