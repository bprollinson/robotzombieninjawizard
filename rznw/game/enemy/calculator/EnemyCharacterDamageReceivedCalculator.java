package rznw.game.enemy.calculator;

import rznw.game.Character;
import rznw.game.StatusEffects;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyStat;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.Zombie;
import rznw.game.stat.Stat;

public class EnemyCharacterDamageReceivedCalculator
{
    public int getDamage(EnemyCharacter enemyCharacter, int damage, Character damageSource, int damageSourceType)
    {
        int paddingPercent = 2 * enemyCharacter.getStatPoints(EnemyStat.STAT_PADDING);

        if (enemyCharacter.getStatusEffects().getStat(StatusEffects.STAT_ARMOR_BREAK) > 0)
        {
            int armorBreakPercent = enemyCharacter.getStatusEffects().getStat(StatusEffects.STAT_ARMOR_BREAK);
            paddingPercent -= armorBreakPercent;

            System.out.println("Enemy armor break percent: " + armorBreakPercent);
        }

        int padding = (int)Math.floor(paddingPercent / 100.0 * damage);

        if (padding != 0)
        {
            System.out.println("Enemy is preventing " + padding + " of " + damage + " damage via padding");
        }

        damage -= padding;

        if (damageSource != null && damageSource.isMainCharacter() && damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            int bonusDamagePercent = 5 * ((MainCharacter)damageSource).getStatPoints(Stat.STAT_MANA_BURN);

            if (bonusDamagePercent > 0)
            {
                 int bonusDamage = (int)Math.floor(bonusDamagePercent / 100.0 * damage);
                 damage += bonusDamage;

                 System.out.println("Mana burn bonus damage: " + bonusDamage);
            }
        }

        if (damageSource instanceof Zombie && damageSource.getStatusEffects().getStatusEffectTurns(StatusEffects.EFFECT_INFECTIOUS_RAGE) > 0)
        {
            System.out.println("Infectious rage is enabled");
            System.out.println("Base damage: " + damage);
            damage = (int)Math.floor(1.5 * damage);
            System.out.println("Rage damage: " + damage);
        }

        if (damageSource instanceof Zombie && damageSource.getStatusEffects().getStatusEffectTurns(StatusEffects.EFFECT_FEED_BRAIN) > 0 && damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            System.out.println("Feed brain is enabled");
            System.out.println("Base damage: " + damage);
            damage = (int)Math.floor(1.6 * damage);
            System.out.println("Feed brain damage: " + damage);
        }

        return damage;
    }
}
