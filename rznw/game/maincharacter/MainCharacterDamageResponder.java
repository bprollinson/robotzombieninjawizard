package rznw.game.maincharacter;

import rznw.game.Character;
import rznw.game.skill.Skill;
import rznw.game.spell.ninja.NinjaSpellFactory;
import rznw.game.spell.ninja.SmokeBombSpell;
import rznw.game.spell.zombie.ZombieSpellFactory;
import rznw.game.statuseffects.StatusEffects;
import rznw.map.GameWorld;
import rznw.utility.RandomNumberGenerator;

public class MainCharacterDamageResponder
{
    public void respondToDamage(GameWorld gameWorld, MainCharacter mainCharacter, Character damageSource, int damage)
    {
        if (mainCharacter.getStatusEffects().getStatusEffectTurns(StatusEffects.EFFECT_MANA_SUCK) > 0)
        {
            int MPFromDamage = (int)Math.floor(5.0 / 100.0 * mainCharacter.getSkills().getSkillPoints(Skill.SKILL_MANA_SUCK) * damage);
            if (MPFromDamage > 0)
            {
                System.out.println("Healing MP from damage: " + MPFromDamage);
                mainCharacter.healMP(MPFromDamage);
            }
        }

        if (damageSource == null)
        {
            return;
        }

        if (mainCharacter.getStatusEffects().getStatusEffect(StatusEffects.EFFECT_COUNTERSTRIKE) && damageSource.isEnemy())
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

            mainCharacter.getStatusEffects().setStatusEffect(StatusEffects.EFFECT_COUNTERSTRIKE, false);
        }

        if (mainCharacter.getStatusEffects().getStatusEffectTurns(StatusEffects.EFFECT_THORN_SKIN) > 0 && damageSource.isEnemy())
        {
            System.out.println("Attacking back with thorn skin");

            int thornSkinDamage = 5 * mainCharacter.getSpells().getSpellPoints(ZombieSpellFactory.SPELL_THORN_SKIN);
            System.out.println("Enemy hp before: " + damageSource.getHP());
            damageSource.damage(thornSkinDamage, mainCharacter, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("Enemy hp after: " + damageSource.getHP());
        }

        if (mainCharacter.getStatusEffects().getStatusEffectTurns(StatusEffects.EFFECT_POISON_SKIN) > 0 && damageSource.isEnemy())
        {
            System.out.println("Poisoning with poison skin");

            damageSource.getStatusEffects().poison();
        }

        if (mainCharacter.getStatusEffects().getStatusEffect(StatusEffects.EFFECT_DEATH_STRIKE) && damageSource.isEnemy())
        {
            System.out.println("Checking death strike");

            int deathStrikeProbability = 5 * mainCharacter.getSpells().getSpellPoints(NinjaSpellFactory.SPELL_DEATH_STRIKE);
            if (RandomNumberGenerator.rollSucceeds(deathStrikeProbability))
            {
                System.out.println("Killing with death strike");
                damageSource.setHP(0);
            }

            mainCharacter.getStatusEffects().setStatusEffect(StatusEffects.EFFECT_DEATH_STRIKE, false);
        }

        if (mainCharacter.getStatusEffects().getStatusEffect(StatusEffects.EFFECT_SMOKE_BOMB) && damageSource.isEnemy())
        {
            System.out.println("Checking smoke bomb");

            int smokeBombProbability = 5 * mainCharacter.getSpells().getSpellPoints(NinjaSpellFactory.SPELL_SMOKE_BOMB);
            if (RandomNumberGenerator.rollSucceeds(smokeBombProbability))
            {
                System.out.println("Escaping with smoke bomb");
                SmokeBombSpell.escape(gameWorld);
            }

            mainCharacter.getStatusEffects().setStatusEffect(StatusEffects.EFFECT_SMOKE_BOMB, false);
        }

        if (mainCharacter.getStatusEffects().getStatusEffectTurns(StatusEffects.EFFECT_BARBED_SKIN) > 0 && damageSource.isEnemy())
        {
            System.out.println("Checking barbed skin");

            int barbedSkinProbability = 5 * mainCharacter.getSpells().getSpellPoints(ZombieSpellFactory.SPELL_BARBED_SKIN);
            if (RandomNumberGenerator.rollSucceeds(barbedSkinProbability))
            {
                System.out.println("Stunning with barbed skin");
                damageSource.getStatusEffects().setStatusEffectTurns(StatusEffects.EFFECT_FROZEN, 2);
            }
        }
    }
}
