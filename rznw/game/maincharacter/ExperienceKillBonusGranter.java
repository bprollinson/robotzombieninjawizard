package rznw.game.maincharacter;

import rznw.game.enemy.EnemyCharacter;
import rznw.ui.LogRendererFactory;

public class ExperienceKillBonusGranter implements KillBonusGranterComponent
{
    public void grantKillBonuses(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
    {
        int oldLevel = mainCharacter.getExperience().getLevel();

        int experience = enemyCharacter.getExperienceReward();
        int maxExperience = ExperienceCalculator.getMaxExperience();
        experience = Math.min(experience, maxExperience - mainCharacter.getExperience().getExperience());
        mainCharacter.getExperience().grantExperience(experience);
        int newLevel = ExperienceCalculator.getLevel(mainCharacter.getExperience().getExperience());

        if (newLevel > oldLevel)
        {
            LogRendererFactory.instance().log("You've leveled up to level " + newLevel + ".");
            mainCharacter.getExperience().setLevel(newLevel);
            mainCharacter.getExperience().setPendingLevels(newLevel - oldLevel);
        }
    }
}
