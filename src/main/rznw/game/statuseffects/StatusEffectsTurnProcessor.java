package rznw.game.statuseffects;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;
import rznw.utility.StringUtils;

import java.util.Iterator;
import java.util.Map;

public class StatusEffectsTurnProcessor
{
    private static final int POISON_DAMAGE = 10;

    private StatusEffectsLogger logger;

    public StatusEffectsTurnProcessor()
    {
        this.logger = new StatusEffectsLogger();
    }

    public void processTurn(GameWorld gameWorld, Character character)
    {
        StatusEffects statusEffects = character.getStatusEffects();

        if (statusEffects.getStatusEffect(SimpleStatusEffects.EFFECT_POISONED))
        {
            int damageDealt = character.damage(StatusEffectsTurnProcessor.POISON_DAMAGE, null, gameWorld, Character.DAMAGE_SOURCE_OTHER);

            if (character instanceof EnemyCharacter)
            {
                StringUtils utils = new StringUtils();
                LogRendererFactory.instance().log(utils.UCFirst(character.getLogName()) + " takes " + damageDealt + " damage from poison.");
            }
            else
            {
                LogRendererFactory.instance().log("You take " + damageDealt + " damage from poison.");
            }
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

            if (turnsRemaining - 1 == 0)
            {
                this.logger.logTurnBasedEffectExhausted(character, pair.getKey());
            }
        }
    }
}
