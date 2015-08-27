public class Map
{
    public static final int NUM_ROWS = 20;
    public static final int NUM_COLUMNS = 20;

    private MapElement[][] elements;

    public Map()
    {
        this.elements = new MapElement[Map.NUM_ROWS][Map.NUM_COLUMNS];
    }

    public MapElement getElement(int i, int j)
    {
        return this.elements[i][j];
    }

    public void setElement(int i, int j, MapElement element)
    {
        this.elements[i][j] = element;
    }
}
