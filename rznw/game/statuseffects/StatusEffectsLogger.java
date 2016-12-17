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
            case TurnBasedStatusEffects.EFFECT_SKIP:
                LogRendererFactory.instance().log("You are no longer incapacitated.");
                break;
            case TurnBasedStatusEffects.EFFECT_MEAT_SHIELD:
                LogRendererFactory.instance().log("Meat shield expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_POWER_SEARCH:
                LogRendererFactory.instance().log("Power search expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_FROZEN:
                if (character.isMainCharacter())
                {
                    LogRendererFactory.instance().log("You are no longer incapacitated.");
                }
                else
                {
                    LogRendererFactory.instance().log(utils.UCFirst(character.getLogName()) + " is not longer incapacitated.");
                }
                break;
        }
    }
}
