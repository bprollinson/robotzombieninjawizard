package rznw.map.element;

public class TrapMapElement extends MapElement
{
    private boolean sprung = false;

    public TrapMapElement(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        if (this.sprung)
        {
            return '-';
        }

        return ' ';
    }

    public boolean isSprung()
    {
        return sprung;
    }

    public void spring()
    {
        this.sprung = true;
    }
}
