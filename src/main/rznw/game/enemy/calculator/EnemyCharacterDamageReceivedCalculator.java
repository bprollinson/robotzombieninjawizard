package rznw.game.enemy.calculator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyStat;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.Zombie;
import rznw.game.stat.Stat;
import rznw.game.statuseffects.StatusEffectStats;
import rznw.game.statuseffects.TurnBasedStatusEffects;

public class EnemyCharacterDamageReceivedCalculator
{
    public int getDamage(EnemyCharacter enemyCharacter, int damage, Character damageSource, int damageSourceType)
    {
        int paddingPercent = 2 * enemyCharacter.getStatPoints(EnemyStat.STAT_PADDING);

        if (enemyCharacter.getStatusEffects().getStat(StatusEffectStats.STAT_ARMOR_BREAK) > 0)
        {
            int armorBreakPercent = enemyCharacter.getStatusEffects().getStat(StatusEffectStats.STAT_ARMOR_BREAK);
            paddingPercent -= armorBreakPercent;
        }

        int padding = (int)Math.floor(paddingPercent / 100.0 * damage);

        damage -= padding;

        if (damageSource != null && damageSource.isMainCharacter() && damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            int bonusDamagePercent = 5 * ((MainCharacter)damageSource).getStats().getStatPoints(Stat.STAT_MANA_BURN);

            if (bonusDamagePercent > 0)
            {
                 int bonusDamage = (int)Math.floor(bonusDamagePercent / 100.0 * damage);
                 damage += bonusDamage;
            }
        }

        if (damageSource instanceof Zombie && damageSource.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_INFECTIOUS_RAGE) > 0)
        {
            damage = (int)Math.floor(1.5 * damage);
        }

        if (damageSource instanceof Zombie && damageSource.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_FEED_BRAIN) > 0 && damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            damage = (int)Math.floor(1.6 * damage);
        }

        return damage;
    }
}
