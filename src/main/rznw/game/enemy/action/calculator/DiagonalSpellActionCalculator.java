package rznw.game.enemy.action.calculator;

import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.DiagonalSpellChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.MovementChoice;
import rznw.game.enemy.action.choice.SummonAttackChoice;

public class DiagonalSpellActionCalculator extends EnemyActionCalculator
{
    private static final int SPELL_INDEX = 0;

    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new SummonAttackChoice(),
            new ConfusionChoice(),
            new DiagonalSpellChoice(DiagonalSpellActionCalculator.SPELL_INDEX),
            new MovementChoice()
        };
    }
}
