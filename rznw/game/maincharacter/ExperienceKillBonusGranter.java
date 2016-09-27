package rznw.game.maincharacter;

import rznw.game.enemy.EnemyCharacter;

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
            System.out.println("Leveling up " + (newLevel - oldLevel) + " time(s) to level " + newLevel);
            mainCharacter.getExperience().setLevel(newLevel);
            mainCharacter.getExperience().setPendingLevels(newLevel - oldLevel);
        }
    }
}
