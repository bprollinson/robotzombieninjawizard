package rznw.game.statuseffects;

import rznw.game.Character;
import rznw.map.GameWorld;

import java.util.Iterator;
import java.util.Map;

public class StatusEffectsTurnProcessor
{
    private static final int POISON_DAMAGE = 10;

    public void processTurn(GameWorld gameWorld, Character character)
    {
        StatusEffects statusEffects = character.getStatusEffects();

        if (statusEffects.getStatusEffect(SimpleStatusEffects.EFFECT_POISONED))
        {
            System.out.println("Damaging character due to poison");
            character.damage(StatusEffectsTurnProcessor.POISON_DAMAGE, null, gameWorld, Character.DAMAGE_SOURCE_OTHER);
        }

        statusEffects.setStatusEffect(SimpleStatusEffects.EFFECT_REVERSE_PAIN, false);

        Iterator<Map.Entry<Integer, Integer>> iterator = statusEffects.getTurnBasedStatusEffects().getEntrySetIterator();
        while (iterator.hasNext())
        {
            Map.Entry<Integer, Integer> pair = iterator.next();
            int turnsRemaining = pair.getValue();

            if (turnsRemaining > 0)
            {
                statusEffects.setStatusEffectTurns(pair.getKey(), turnsRemaining - 1);
            }
        }
    }
}
