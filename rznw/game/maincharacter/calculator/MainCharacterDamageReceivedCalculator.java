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
import rznw.game.statuseffects.StatusEffectStats;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class MainCharacterDamageReceivedCalculator
{
    public int getDamage(MainCharacter mainCharacter, int damage, Character damageSource, int damageSourceType)
    {
        if (damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            int dodgePercent = 5 * mainCharacter.getSkills().getSkillPoints(Skill.SKILL_PROTECTIVE_FIELD);

            Shield shield = mainCharacter.getEquipment().getEquippedShield();
            if (shield != null)
            {
                int shieldDodgePercent = shield.getMagicDodgePercent();
                dodgePercent += shieldDodgePercent;
            }

            if (RandomNumberGenerator.rollSucceeds(dodgePercent))
            {
                LogRendererFactory.instance().log("Dodged magic damage.");
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
                magicPaddingPercent += shieldPaddingPercent;
            }

            paddingPercent += magicPaddingPercent;
        }

        if (mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_RAGE) > 0)
        {
            int paddingPenalty = Math.max(21 - mainCharacter.getSkills().getSkillPoints(Skill.SKILL_RAGE), 1);
            paddingPercent -= paddingPenalty;
        }

        Shield shield = mainCharacter.getEquipment().getEquippedShield();
        if (shield != null)
        {
            int shieldPaddingPercent = shield.getPaddingPercent();
            paddingPercent += shieldPaddingPercent;
        }

        Armor armor = mainCharacter.getEquipment().getEquippedArmor();
        if (armor != null)
        {
            int armorPaddingPercent = armor.getPaddingPercent();
            paddingPercent += armorPaddingPercent;
        }

        if (mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_MEAT_SHIELD) > 0)
        {
            int meatShieldPaddingPercent = mainCharacter.getStatusEffects().getStat(StatusEffectStats.STAT_MEAT_SHIELD_PADDING_PERCENT);
            paddingPercent += meatShieldPaddingPercent;
        }

        int padding = (int)Math.floor(paddingPercent / 100.0 * damage);

        if (damageSource != null && damageSource.isEnemy() && damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            int bonusDamagePercent = 5 * ((EnemyCharacter)damageSource).getStatPoints(EnemyStat.STAT_MANA_BURN);

            if (bonusDamagePercent > 0)
            {
                 int bonusDamage = (int)Math.floor(bonusDamagePercent / 100.0 * (damage - padding));
                 damage += bonusDamage;
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
