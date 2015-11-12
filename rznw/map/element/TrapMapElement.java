package rznw.map.element;

public class TrapMapElement extends MapElement
{
    private boolean visible = false;
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

        if (this.visible)
        {
            return '_';
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

    public void find()
    {
        this.visible = true;
    }
}
