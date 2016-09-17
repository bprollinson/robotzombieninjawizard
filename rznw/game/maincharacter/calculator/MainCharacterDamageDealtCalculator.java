package rznw.game.maincharacter.calculator;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Weapon;
import rznw.game.skill.Skill;
import rznw.game.stat.Stat;

public class MainCharacterDamageDealtCalculator
{
    public int getDamage(MainCharacter mainCharacter)
    {
        int damage = 50 + 5 * mainCharacter.getStatPoints(Stat.STAT_DAMAGE);

        Weapon weapon = mainCharacter.getEquipment().getEquippedWeapon();
        if (weapon != null)
        {
            int weaponDamage = weapon.getDamage();
            System.out.println("Additional weapon damage: " + weaponDamage);
            damage += weaponDamage;
        }

        if (mainCharacter.getStatusEffects().rageEnabled())
        {
            int bonusDamagePercent = 2 * mainCharacter.getSkillPoints(Skill.SKILL_RAGE);
            int bonusDamage = (int)Math.floor(bonusDamagePercent / 100.0 * damage);
            System.out.println("Bonus rage damage: " + bonusDamage);
        }

        double bloodRageFactor = mainCharacter.getSkillPoints(Skill.SKILL_BLOOD_RAGE) / 100.0 * (mainCharacter.getMaxHP() - mainCharacter.getHP()) / mainCharacter.getMaxHP();

        if (bloodRageFactor > 0.0) {
            System.out.println("Blood rage factor: " + bloodRageFactor);
        }

        return (int)Math.floor(damage * (1 + bloodRageFactor));
    }
}
