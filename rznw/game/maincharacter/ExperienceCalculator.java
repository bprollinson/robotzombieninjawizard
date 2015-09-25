package rznw.game.maincharacter;

public class ExperienceCalculator
{
    public static int getLevel(int experience)
    {
        return (int)Math.floor(experience / 20);
    }

    public static int getRequiredExperience(int level)
    {
        return 20 * level;
    }
}
