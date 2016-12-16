package rznw.game.statuseffects;

import rznw.game.Character;
import rznw.ui.LogRendererFactory;

public class StatusEffectsLogger
{
    public void logTurnBasedEffectExhausted(Character character, int index)
    {
        switch (index)
        {
            case TurnBasedStatusEffects.EFFECT_SKIP:
                LogRendererFactory.instance().log("You are no longer incapacitated.");
                break;
        }
    }
}
