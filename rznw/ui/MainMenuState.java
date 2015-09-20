package rznw.ui;

public class MainMenuState
{
    private static int MAX_ENTRY_NUMBER = 7;

    private int entryNumber = 0;

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
        if (this.entryNumber < MainMenuState.MAX_ENTRY_NUMBER)
        {
            this.entryNumber++;
        }
    }
}
