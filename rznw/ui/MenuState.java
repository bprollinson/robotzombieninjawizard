package rznw.ui;

public class MenuState
{
    private int maxEntryNumber;
    private int entryNumber = 0;

    public MenuState(int numEntries)
    {
        this.maxEntryNumber = numEntries - 1;
    }

    public int getEntryNumber()
    {
        return this.entryNumber;
    }

    public void setEntryNumber(int entryNumber)
    {
        this.entryNumber = entryNumber;
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

    public int getMaxEntryNumber()
    {
        return this.maxEntryNumber;
    }

    public void adjustMaxEntryNumber(int maxEntryNumber)
    {
        this.maxEntryNumber = maxEntryNumber;

        this.entryNumber = Math.min(this.entryNumber, this.maxEntryNumber);
    }
}
