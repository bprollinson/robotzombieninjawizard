package rznw.game.maincharacter.calculator;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Weapon;
import rznw.game.skill.Skill;
import rznw.game.stat.Stat;
import rznw.game.statuseffects.TurnBasedStatusEffects;

public class MainCharacterDamageDealtCalculator
{
    public int getDamage(MainCharacter mainCharacter)
    {
        int damage = 50 + 5 * mainCharacter.getStats().getStatPoints(Stat.STAT_DAMAGE);

        Weapon weapon = mainCharacter.getEquipment().getEquippedWeapon();
        if (weapon != null)
        {
            int weaponDamage = weapon.getDamage();
            damage += weaponDamage;
        }

        if (mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_RAGE) > 0)
        {
            int bonusDamagePercent = 2 * mainCharacter.getSkills().getSkillPoints(Skill.SKILL_RAGE);
            int bonusDamage = (int)Math.floor(bonusDamagePercent / 100.0 * damage);
        }

        double bloodRageFactor = mainCharacter.getSkills().getSkillPoints(Skill.SKILL_BLOOD_RAGE) / 100.0 * (mainCharacter.getMaxHP() - mainCharacter.getHP()) / mainCharacter.getMaxHP();

        return (int)Math.floor(damage * (1 + bloodRageFactor));
    }
}
