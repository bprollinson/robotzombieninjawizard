package rznw.game.statuseffects;

import rznw.game.Character;
import rznw.ui.LogRendererFactory;
import rznw.utility.StringUtils;

public class StatusEffectsLogger
{
    public void logTurnBasedEffectExhausted(Character character, int index)
    {
        StringUtils utils = new StringUtils();

        switch (index)
        {
            case TurnBasedStatusEffects.EFFECT_SIGNAL_WEAPON:
                LogRendererFactory.instance().log("Signal weapon expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_THORN_SKIN:
                LogRendererFactory.instance().log("Thorn skin expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_POISON_SKIN:
                LogRendererFactory.instance().log("Poison skin expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_BARBED_SKIN:
                LogRendererFactory.instance().log("Barbed skin expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_RESIST_DAMAGE:
                LogRendererFactory.instance().log("Resist damage expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_INFECTIOUS_RAGE:
                LogRendererFactory.instance().log("Infectious rage expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_FEED_BRAIN:
                LogRendererFactory.instance().log("Feed brain expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_SKIP:
                LogRendererFactory.instance().log("You are no longer incapacitated.");
                break;
            case TurnBasedStatusEffects.EFFECT_RAGE:
                LogRendererFactory.instance().log("Rage expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_MAGIC_SEEDS:
                LogRendererFactory.instance().log("Magic seeds expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_MANA_SUCK:
                LogRendererFactory.instance().log("Mana suck expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_MEAT_SHIELD:
                LogRendererFactory.instance().log("Meat shield expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_POWER_SEARCH:
                LogRendererFactory.instance().log("Power search expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_CONFUSION:
                if (character.isMainCharacter())
                {
                    LogRendererFactory.instance().log("You are no longer confused.");
                }
                else
                {
                    LogRendererFactory.instance().log(utils.UCFirst(character.getLogName()) + " is no longer confused.");
                }
                break;
            case TurnBasedStatusEffects.EFFECT_FROZEN:
                if (character.isMainCharacter())
                {
                    LogRendererFactory.instance().log("You are no longer incapacitated.");
                }
                else
                {
                    LogRendererFactory.instance().log(utils.UCFirst(character.getLogName()) + " is no longer incapacitated.");
                }
                break;
        }
    }
}
