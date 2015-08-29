import rznw.GameCharacter;
import rznw.MainGameFrame;
import rznw.Map;
import rznw.MapGenerator;
import rznw.MapRenderer;
import rznw.MovementKeyListener;

public class Test
{
    public static void main(String[] args)
    {
        MainGameFrame frame = new MainGameFrame("Robot Zombie Ninja Wizard");
        frame.setVisible(true);

        GameCharacter character = new GameCharacter();

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
