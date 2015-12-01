package rznw.map.element;

public class FireElement extends MapElement
{
    private MapElement previousMapElement;
    private int duration;

    public FireElement(int row, int column, MapElement previousMapElement, int duration)
    {
        super(row, column);

        this.previousMapElement = previousMapElement;
        this.duration = duration;
    }

    public void decreaseDuration()
    {
        this.duration--;
    }

    public boolean isExpired()
    {
        return this.duration <= 0;
    }

    public MapElement getPreviousMapElement()
    {
        return this.previousMapElement;
    }

    public char getDisplayCharacter()
    {
        return 'F';
    }
}
