package rznw.game.maincharacter.calculator;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Weapon;

public class MainCharacterDamageDealtCalculator
{
    public int getDamage(MainCharacter mainCharacter)
    {
        int damage = 50 + 5 * mainCharacter.getStatPoints(8);

        Weapon weapon = mainCharacter.getEquipment().getEquippedWeapon();
        if (weapon != null)
        {
            int weaponDamage = weapon.getDamage();
            System.out.println("Additional weapon damage: " + weaponDamage);
            damage += weaponDamage;
        }

        if (mainCharacter.getStatusEffects().rageEnabled())
        {
            int bonusDamagePercent = 2 * mainCharacter.getSkillPoints(9);
            int bonusDamage = (int)Math.floor(bonusDamagePercent / 100.0 * damage);
            System.out.println("Bonus rage damage: " + bonusDamage);
        }

        double bloodRageFactor = mainCharacter.getSkillPoints(2) / 100.0 * (mainCharacter.getMaxHP() - mainCharacter.getHP()) / mainCharacter.getMaxHP();

        if (bloodRageFactor > 0.0) {
            System.out.println("Blood rage factor: " + bloodRageFactor);
        }

        return (int)Math.floor(damage * (1 + bloodRageFactor));
    }
}
