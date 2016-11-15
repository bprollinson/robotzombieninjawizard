package rznw.turn;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Shield;
import rznw.game.spell.robot.RobotSpellFactory;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.CharacterMapElement;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;
import rznw.utility.StringUtils;

public class CharacterInteracter
{
    private StringUtils stringUtils = new StringUtils();

    public void handleCharacterInteraction(Character character, CharacterMapElement collisionTest, Map map, GameWorld gameWorld)
    {
        Character otherCharacter = ((CharacterMapElement)collisionTest).getCharacter();
        String verb = "attacks";
        if (character.isMainCharacter())
        {
            verb = "attack";
        }
        LogRendererFactory.instance().log(this.stringUtils.UCFirst(character.getLogName()) + " " + verb + " " + otherCharacter.getLogName() + ".");

        if (!character.meleeAttackHits())
        {
            if (character.isMainCharacter())
            {
                System.out.println("Main character melee miss!");
            }
            else
            {
                System.out.println("Enemy character melee miss!");
            }

            return;
        }

        if (otherCharacter.dodgesAttack())
        {
            if (otherCharacter.isMainCharacter())
            {
                System.out.println("Main character dodge!");

                Shield shield = ((MainCharacter)otherCharacter).getEquipment().getEquippedShield();
                if (shield != null)
                {
                    shield.dodgesAttack((MainCharacter)otherCharacter);
                }
            }
            else
            {
                System.out.println("Enemy character dodge!");
            }

            return;
        }

        if (character.isMainCharacter())
        {
            System.out.println("Main character melee hit!");
        }
        else
        {
            System.out.println("Enemy character melee hit!");
        }

        if (otherCharacter.isSummon())
        {
            System.out.println("Summoned character HP before: " + otherCharacter.getHP());
        }

        if (otherCharacter.isEnemy() && character.isSummon())
        {
            System.out.println("Summon is hitting an enemy, HP before: " + otherCharacter.getHP());
        }

        int damage = otherCharacter.damage(character.getDamage(), character, gameWorld, Character.DAMAGE_SOURCE_PHYSICAL);

        if (otherCharacter.isEnemy() && character.isSummon())
        {
            System.out.println("Summon is hitting an enemy, HP after: " + otherCharacter.getHP());
        }

        if (otherCharacter.isSummon())
        {
            System.out.println("Summoned character HP after: " + otherCharacter.getHP());
        }

        if (character.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_SIGNAL_WEAPON) > 0)
        {
            System.out.println("Checking signal weapon");

            int confuseProbability = 5 * ((MainCharacter)character).getSpells().getSpellPoints(RobotSpellFactory.SPELL_SIGNAL_WEAPON);
            System.out.println("Confuse probability: " + confuseProbability);

            if (RandomNumberGenerator.rollSucceeds(confuseProbability))
            {
                System.out.println("Confuse successful!");
                otherCharacter.getStatusEffects().confuse();
            }
        }

        if (character.isEnemy() && otherCharacter.isMainCharacter())
        {
            ((EnemyCharacter)character).damagedMainCharacter((MainCharacter)otherCharacter, damage, gameWorld);
            ((MainCharacter)otherCharacter).damagedByEnemyCharacter((EnemyCharacter)character, damage, gameWorld);
        }
        else if (character.isMainCharacter() && otherCharacter.isEnemy())
        {
            ((MainCharacter)character).damagedEnemyCharacter((EnemyCharacter)otherCharacter, damage, gameWorld);
            ((EnemyCharacter)otherCharacter).damagedByMainCharacter((MainCharacter)character, damage, gameWorld);
        }
    }
}
