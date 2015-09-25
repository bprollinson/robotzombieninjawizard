package rznw.ui;

public class MenuState
{
    private int maxEntryNumber;
    private int entryNumber = 0;

    public MenuState(int maxEntryNumber)
    {
        this.maxEntryNumber = maxEntryNumber;
    }

    public int getEntryNumber()
    {
        return this.entryNumber;
    }

    public void moveUp()
    {
        if (this.entryNumber > 0)
        {
            this.entryNumber--;
        }
    }

    public void moveDown()
    {
        if (this.entryNumber < this.maxEntryNumber)
        {
            this.entryNumber++;
        }
    }
}
