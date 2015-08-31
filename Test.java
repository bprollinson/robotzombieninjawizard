import rznw.game.CharacterGenerator;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.Map;
import rznw.map.MapGenerator;
import rznw.turn.MainCharacterTurnHandler;
import rznw.ui.MainGameFrame;
import rznw.ui.MapRenderer;
import rznw.ui.MovementKeyListener;

public class Test
{
    public static void main(String[] args)
    {
        CharacterGenerator characterGenerator = new CharacterGenerator();
        MainCharacter character = characterGenerator.generateMainCharacter();

        MapGenerator mapGenerator = new MapGenerator();
        Map map = mapGenerator.generate(character, characterGenerator);

        MainGameFrame frame = new MainGameFrame("Robot Zombie Ninja Wizard");
        MapRenderer renderer = new MapRenderer(frame);
        renderer.render(map);

        MainCharacterTurnHandler turnHandler = new MainCharacterTurnHandler(map, character);
        MovementKeyListener listener = new MovementKeyListener(turnHandler, renderer, map);

        frame.display(listener);
    }
}
