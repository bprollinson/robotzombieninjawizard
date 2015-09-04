package rznw.game.maincharacter;

public class ExperienceCalculator
{
    public static int getLevel(int experience)
    {
        return (int)Math.floor(experience / 20);
    }
}
