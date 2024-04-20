package rznw.game.maincharacter;

import rznw.game.enemy.EnemyCharacter;
import rznw.ui.LogRendererFactory;

public class ExperienceKillBonusGranter implements KillBonusGranterComponent
{
    public void grantKillBonuses(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
    {
        int experience = enemyCharacter.getExperienceReward();
        this.grantExperience(mainCharacter, experience, false);
    }

    public void grantExperience(MainCharacter mainCharacter, int experience, boolean logExperience)
    {
        int oldLevel = mainCharacter.getExperience().getLevel();

        int maxExperience = ExperienceCalculator.getMaxExperience();
        experience = Math.min(experience, maxExperience - mainCharacter.getExperience().getExperience());
        mainCharacter.getExperience().grantExperience(experience);
        int newLevel = ExperienceCalculator.getLevel(mainCharacter.getExperience().getExperience());

        if (logExperience)
        {
            LogRendererFactory.instance().log("Gained " + experience + " bonus experience.");
        }

        if (newLevel > oldLevel)
        {
            LogRendererFactory.instance().log("You've leveled up to level " + newLevel + ".");
            mainCharacter.getExperience().setLevel(newLevel);
            mainCharacter.getExperience().setPendingLevels(newLevel - oldLevel);
        }
    }
}
