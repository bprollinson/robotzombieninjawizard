package rznw.game.maincharacter;

public class MainCharacterExperience
{
    private int level = 0;
    private int experience = 0;
    private int pendingLevels = 0;

    public int getLevel()
    {
        return this.level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getExperience()
    {
        return this.experience;
    }

    public void grantExperience(int experience)
    {
        this.experience += experience;
    }

    public void setExperience(int experience)
    {
        this.experience = experience;
    }

    public int getPendingLevels()
    {
        return this.pendingLevels;
    }

    public void setPendingLevels(int pendingLevels)
    {
        this.pendingLevels = pendingLevels;
    }
}
