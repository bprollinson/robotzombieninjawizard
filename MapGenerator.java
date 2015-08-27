public class MapGenerator
{
    public Map generate()
    {
        Map map = new Map();

        for (int i = 0; i < Map.NUM_COLUMNS; i++)
        {
            map.setElement(0, i, new Wall());
            map.setElement(Map.NUM_ROWS - 1, i, new Wall());
        }

        for (int i = 1; i < Map.NUM_ROWS - 1; i++)
        {
            map.setElement(i, 0, new Wall());
            map.setElement(i, Map.NUM_COLUMNS - 1, new Wall());
        }

        return map;
    }
}
