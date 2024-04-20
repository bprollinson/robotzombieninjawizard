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
            LogRendererFactory.instance().log("Attack misses!");

            return;
        }

        if (otherCharacter.dodgesAttack())
        {
            LogRendererFactory.instance().log("Attack dodged!");
            if (otherCharacter.isMainCharacter())
            {
                Shield shield = ((MainCharacter)otherCharacter).getEquipment().getEquippedShield();
                if (shield != null)
                {
                    shield.dodgesAttack((MainCharacter)otherCharacter);
                }
            }

            return;
        }

        int damage = otherCharacter.damage(character.getDamage(), character, gameWorld, Character.DAMAGE_SOURCE_PHYSICAL);
        if (damage == 0)
        {
            return;
        }

        LogRendererFactory.instance().log("Hit for " + damage + " damage!");

        if (character.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_SIGNAL_WEAPON) > 0)
        {
            int confuseProbability = 5 * ((MainCharacter)character).getSpells().getSpellPoints(RobotSpellFactory.SPELL_SIGNAL_WEAPON);

            if (RandomNumberGenerator.rollSucceeds(confuseProbability))
            {
                LogRendererFactory.instance().log("Enemy confused by signal weapon.");
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
