package rznw.game.maincharacter.calculator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyStat;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Armor;
import rznw.game.maincharacter.inventory.Shield;
import rznw.game.skill.Skill;
import rznw.game.spell.zombie.ZombieSpellFactory;
import rznw.game.stat.Stat;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.game.statuseffects.StatusEffects;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.utility.RandomNumberGenerator;

public class MainCharacterDamageReceivedCalculator
{
    public int getDamage(MainCharacter mainCharacter, int damage, Character damageSource, int damageSourceType)
    {
        if (damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            System.out.println("Hit by a magical source");
            int dodgePercent = 5 * mainCharacter.getSkills().getSkillPoints(Skill.SKILL_PROTECTIVE_FIELD);
            System.out.println("Magic dodge change: " + dodgePercent);

            Shield shield = mainCharacter.getEquipment().getEquippedShield();
            if (shield != null)
            {
                int shieldDodgePercent = shield.getMagicDodgePercent();
                System.out.println("Shield magic dodge percent: " + shieldDodgePercent);
                dodgePercent += shieldDodgePercent;
            }

            if (RandomNumberGenerator.rollSucceeds(dodgePercent))
            {
                System.out.println("Successfully dodged magic");
                return 0;
            }
        }

        int paddingPercent = 2 * mainCharacter.getStats().getStatPoints(Stat.STAT_PADDING);
        if (mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_RESIST_DAMAGE) > 0)
        {
            paddingPercent += 2 * mainCharacter.getSpells().getSpellPoints(ZombieSpellFactory.SPELL_RESIST_DAMAGE);
        }

        if (damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            int magicPaddingPercent = 5 * mainCharacter.getStats().getStatPoints(Stat.STAT_MAGIC_RESISTANCE);

            Shield shield = mainCharacter.getEquipment().getEquippedShield();
            if (shield != null)
            {
                int shieldPaddingPercent = shield.getMagicPaddingPercent();
                System.out.println("Shield magic padding percent: " + shieldPaddingPercent);
                magicPaddingPercent += shieldPaddingPercent;
            }

            System.out.println("Preventing " + magicPaddingPercent + "% of damage");
            paddingPercent += magicPaddingPercent;
        }

        if (mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_RAGE) > 0)
        {
            int paddingPenalty = Math.max(21 - mainCharacter.getSkills().getSkillPoints(Skill.SKILL_RAGE), 1);
            System.out.println("Padding penalty: " + paddingPenalty);
            paddingPercent -= paddingPenalty;
        }

        Shield shield = mainCharacter.getEquipment().getEquippedShield();
        if (shield != null)
        {
            int shieldPaddingPercent = shield.getPaddingPercent();
            System.out.println("Additional shield padding percent: " + shieldPaddingPercent);
            paddingPercent += shieldPaddingPercent;
        }

        Armor armor = mainCharacter.getEquipment().getEquippedArmor();
        if (armor != null)
        {
            int armorPaddingPercent = armor.getPaddingPercent();
            System.out.println("Additional armor padding percent: " + armorPaddingPercent);
            paddingPercent += armorPaddingPercent;
        }

        if (mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_MEAT_SHIELD) > 0)
        {
            int meatShieldPaddingPercent = mainCharacter.getStatusEffects().getStat(StatusEffects.STAT_MEAT_SHIELD_PADDING_PERCENT);
            System.out.println("Additional meat shield pardding percent: " + meatShieldPaddingPercent);
            paddingPercent += meatShieldPaddingPercent;
        }

        int padding = (int)Math.floor(paddingPercent / 100.0 * damage);

        if (padding > 0)
        {
            System.out.println("Padding percent: " + paddingPercent);
            System.out.println("Padding damage: " + padding);
        }

        if (damageSource != null && damageSource.isEnemy() && damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            int bonusDamagePercent = 5 * ((EnemyCharacter)damageSource).getStatPoints(EnemyStat.STAT_MANA_BURN);

            if (bonusDamagePercent > 0)
            {
                 int bonusDamage = (int)Math.floor(bonusDamagePercent / 100.0 * (damage - padding));
                 damage += bonusDamage;

                 System.out.println("Enemy mana burn bonus damage: " + bonusDamage);
            }
        }

        if (mainCharacter.getStatusEffects().getStatusEffect(SimpleStatusEffects.EFFECT_REVERSE_PAIN))
        {
            System.out.println("Reversing pain!");

            return -(damage - padding);
        }

        return damage - padding;
    }
}
