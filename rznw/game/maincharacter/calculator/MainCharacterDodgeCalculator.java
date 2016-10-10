package rznw.game.maincharacter.calculator;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Armor;
import rznw.game.maincharacter.inventory.Shield;
import rznw.game.skill.Skill;
import rznw.game.stat.Stat;
import rznw.game.statuseffects.StatusEffects;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.utility.RandomNumberGenerator;

public class MainCharacterDodgeCalculator
{
    public boolean dodgesAttack(MainCharacter mainCharacter)
    {
        int toDodgePercent = 2 * mainCharacter.getStats().getStatPoints(Stat.STAT_DODGE);

        Shield shield = mainCharacter.getEquipment().getEquippedShield();
        if (shield != null)
        {
            int shieldDodgePercent = shield.getDodgePercent();
            System.out.println("Additional shield chance to dodge: " + shieldDodgePercent);
            toDodgePercent += shieldDodgePercent;
        }

        Armor armor = mainCharacter.getEquipment().getEquippedArmor();
        if (armor != null)
        {
            int armorDodgePercent = armor.getDodgePercent();
            System.out.println("Additional armor chance to dodge: " + armorDodgePercent);
            toDodgePercent += armorDodgePercent;
        }

        if (mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_RAGE) > 0)
        {
            int dodgePenalty = Math.max(21 - mainCharacter.getSkills().getSkillPoints(Skill.SKILL_RAGE), 1);
            System.out.println("Dodge penalty: " + dodgePenalty);
            toDodgePercent -= dodgePenalty;
        }

        if (mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_MEAT_SHIELD) > 0)
        {
            int meatShieldDodgePercent = mainCharacter.getStatusEffects().getStat(StatusEffects.STAT_MEAT_SHIELD_DODGE_PERCENT);
            System.out.println("Meat shield dodge bonus: " + meatShieldDodgePercent);
            toDodgePercent += meatShieldDodgePercent;
        }

        return RandomNumberGenerator.rollSucceeds(toDodgePercent);
    }
}
