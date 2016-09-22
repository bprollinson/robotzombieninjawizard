package rznw.game.maincharacter;

import rznw.game.StatusEffects;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.skill.Skill;
import rznw.utility.RandomNumberGenerator;

public class GoldKillBonusGranter implements KillBonusGranterComponent
{
    public void grantKillBonuses(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
    {
        int baseGold = enemyCharacter.getNumGold();
        int bonusGoldPercent = RandomNumberGenerator.randomInteger(0, mainCharacter.getSkillPoints(Skill.SKILL_ABUNDANCE));
        bonusGoldPercent += enemyCharacter.getStatusEffects().getStat(StatusEffects.STAT_BONUS_GOLD_PERCENT);
        int bonusGold = (int)(baseGold * bonusGoldPercent / 100);

        mainCharacter.getInventory().addGold(baseGold + bonusGold);
    }
}
