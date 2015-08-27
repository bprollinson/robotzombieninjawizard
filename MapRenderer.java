public class MapRenderer
{
    private MainGameFrame frame;

    public MapRenderer(MainGameFrame frame)
    {
        this.frame = frame;
    }

    public void render(Map map)
    {
        for (int i = 0; i < Map.NUM_ROWS; i++)
        {
            for (int j = 0; j < Map.NUM_COLUMNS; j++)
            {
                MapElement element = map.getElement(i, j);

                if (element != null)
                {
                    char displayCharacter = element.getDisplayCharacter();
                    this.frame.renderDisplayCharacter(i, j, displayCharacter);
                }
            }
        }
    }
}
