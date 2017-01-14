package rznw.game.enemy.action.calculator;

import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.DamagedEscapeChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.MovementChoice;
import rznw.game.enemy.action.choice.PartialHealSpellChoice;
import rznw.game.enemy.action.choice.SummonAttackChoice;

public class EscapeAndHealActionCalculator extends EnemyActionCalculator
{
    private static final int SPELL_INDEX = 0;
    private static final int RADIUS = 2;

    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new SummonAttackChoice(),
            new ConfusionChoice(),
            new DamagedEscapeChoice(EscapeAndHealActionCalculator.RADIUS),
            new PartialHealSpellChoice(EscapeAndHealActionCalculator.SPELL_INDEX),
            new MovementChoice()
        };
    }
}
