package rznw.game.maincharacter;

public class ExperienceCalculator
{
    public static int getLevel(int experience)
    {
        return (int)Math.floor(Math.sqrt(experience / 20));
    }

    public static int getRequiredExperience(int level)
    {
        return 20 * level * level;
    }

    public static int getMaxExperience()
    {
        return 20 * MainCharacter.MAX_LEVEL * MainCharacter.MAX_LEVEL;
    }
}
