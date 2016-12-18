package rznw.game.maincharacter;

import rznw.game.Character;
import rznw.game.skill.Skill;
import rznw.game.spell.ninja.NinjaSpellFactory;
import rznw.game.spell.ninja.SmokeBombSpell;
import rznw.game.spell.zombie.ZombieSpellFactory;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class MainCharacterDamageResponder
{
    public void respondToDamage(GameWorld gameWorld, MainCharacter mainCharacter, Character damageSource, int damage)
    {
        if (mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_MANA_SUCK) > 0)
        {
            int MPFromDamage = (int)Math.floor(5.0 / 100.0 * mainCharacter.getSkills().getSkillPoints(Skill.SKILL_MANA_SUCK) * damage);
            if (MPFromDamage > 0)
            {
                LogRendererFactory.instance().log("Recovered " + MPFromDamage + " MP via mana suck.");
                mainCharacter.healMP(MPFromDamage);
            }
        }

        if (damageSource == null)
        {
            return;
        }

        if (mainCharacter.getStatusEffects().getStatusEffect(SimpleStatusEffects.EFFECT_COUNTERSTRIKE) && damageSource.isEnemy())
        {
            System.out.println("Checking counterstrike");

            int counterstrikeProbability = 10 * mainCharacter.getSpells().getSpellPoints(NinjaSpellFactory.SPELL_COUNTERSTRIKE);
            if (RandomNumberGenerator.rollSucceeds(counterstrikeProbability))
            {
                System.out.println("Damaging with counterstrike");

                int counterstrikeDamage = 10 * mainCharacter.getSpells().getSpellPoints(NinjaSpellFactory.SPELL_COUNTERSTRIKE);
                System.out.println("Enemy hp before: " + damageSource.getHP());
                damageSource.damage(counterstrikeDamage, mainCharacter, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
                System.out.println("Enemy hp after: " + damageSource.getHP());
            }

            mainCharacter.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_COUNTERSTRIKE, false);
        }

        if (mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_THORN_SKIN) > 0 && damageSource.isEnemy())
        {
            int thornSkinDamage = 5 * mainCharacter.getSpells().getSpellPoints(ZombieSpellFactory.SPELL_THORN_SKIN);
            int damageDealt = damageSource.damage(thornSkinDamage, mainCharacter, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            LogRendererFactory.instance().log("Dealt " + damageDealt + " HP of damage to " + damageSource.getLogName() + " via thorn skin.");
        }

        if (mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_POISON_SKIN) > 0 && damageSource.isEnemy())
        {
            LogRendererFactory.instance().log("Enemy poisoned by poison skin.");

            damageSource.getStatusEffects().poison();
        }

        if (mainCharacter.getStatusEffects().getStatusEffect(SimpleStatusEffects.EFFECT_DEATH_STRIKE) && damageSource.isEnemy())
        {
            System.out.println("Checking death strike");

            int deathStrikeProbability = 5 * mainCharacter.getSpells().getSpellPoints(NinjaSpellFactory.SPELL_DEATH_STRIKE);
            if (RandomNumberGenerator.rollSucceeds(deathStrikeProbability))
            {
                System.out.println("Killing with death strike");
                damageSource.setHP(0);
            }

            mainCharacter.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_DEATH_STRIKE, false);
        }

        if (mainCharacter.getStatusEffects().getStatusEffect(SimpleStatusEffects.EFFECT_SMOKE_BOMB) && damageSource.isEnemy())
        {
            int smokeBombProbability = 5 * mainCharacter.getSpells().getSpellPoints(NinjaSpellFactory.SPELL_SMOKE_BOMB);
            if (RandomNumberGenerator.rollSucceeds(smokeBombProbability))
            {
                SmokeBombSpell.escape(gameWorld);
                LogRendererFactory.instance().log("Escaped with smoke bomb.");
            }

            mainCharacter.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_SMOKE_BOMB, false);
        }

        if (mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_BARBED_SKIN) > 0 && damageSource.isEnemy())
        {
            int barbedSkinProbability = 5 * mainCharacter.getSpells().getSpellPoints(ZombieSpellFactory.SPELL_BARBED_SKIN);
            if (RandomNumberGenerator.rollSucceeds(barbedSkinProbability))
            {
                LogRendererFactory.instance().log("Enemy stunned by barbed skin.");
                damageSource.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_FROZEN, 2);
            }
        }
    }
}
