package rznw.ui;

public class MenuState
{
    private int numEntries;
    private int entryNumber = 0;

    public MenuState(int numEntries)
    {
        this.numEntries = numEntries;
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
        if (this.entryNumber < this.numEntries - 1)
        {
            this.entryNumber++;
        }
    }

    public boolean hasEntries()
    {
        return this.numEntries > 0;
    }

    public void adjustNumEntries(int numEntries)
    {
        this.numEntries = numEntries;
        this.entryNumber = Math.min(this.entryNumber, this.numEntries - 1);
    }
}
