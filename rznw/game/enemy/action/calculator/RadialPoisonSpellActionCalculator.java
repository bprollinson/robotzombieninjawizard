package rznw.game.enemy.action.calculator;

import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.MovementChoice;
import rznw.game.enemy.action.choice.RadialPoisonSpellChoice;

public class RadialPoisonSpellActionCalculator extends EnemyActionCalculator
{
    private static final int SPELL_INDEX = 0;
    private static final int RADIUS = 3;

    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new ConfusionChoice(),
            new RadialPoisonSpellChoice(RadialPoisonSpellActionCalculator.SPELL_INDEX, RadialPoisonSpellActionCalculator.RADIUS),
            new MovementChoice()
        };
    }
}
