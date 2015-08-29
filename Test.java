import rznw.game.CharacterGenerator;
import rznw.game.GameCharacter;
import rznw.map.Map;
import rznw.map.MapGenerator;
import rznw.ui.MainGameFrame;
import rznw.ui.MapRenderer;
import rznw.ui.MovementKeyListener;

public class Test
{
    public static void main(String[] args)
    {
        MainGameFrame frame = new MainGameFrame("Robot Zombie Ninja Wizard");
        frame.setVisible(true);

        GameCharacter character = CharacterGenerator.generate();

        MapGenerator mapGenerator = new MapGenerator();
        Map map = mapGenerator.generate(character);

        MapRenderer renderer = new MapRenderer(frame);
        renderer.render(map);

        MovementKeyListener listener = new MovementKeyListener(character, map, renderer);
        frame.getContentPane().setFocusable(true);
        frame.getContentPane().requestFocus();
        frame.getContentPane().addKeyListener(listener);
    }
}
