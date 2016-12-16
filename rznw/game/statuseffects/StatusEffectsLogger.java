package rznw.game.statuseffects;

import rznw.game.Character;
import rznw.ui.LogRendererFactory;

public class StatusEffectsLogger
{
    public void logTurnBasedEffectExhausted(Character character, int index)
    {
        switch (index)
        {
            case TurnBasedStatusEffects.EFFECT_SIGNAL_WEAPON:
                LogRendererFactory.instance().log("Signal weapon expired.");
                break;
            case TurnBasedStatusEffects.EFFECT_SKIP:
                LogRendererFactory.instance().log("You are no longer incapacitated.");
                break;
            case TurnBasedStatusEffects.EFFECT_MEAT_SHIELD:
                LogRendererFactory.instance().log("Meat shield expired.");
                break;
        }
    }
}
