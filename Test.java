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
    }
}
