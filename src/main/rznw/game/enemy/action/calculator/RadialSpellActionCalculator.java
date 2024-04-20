package rznw.game.enemy.action.calculator;

import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.MovementChoice;
import rznw.game.enemy.action.choice.RadialSpellChoice;
import rznw.game.enemy.action.choice.SummonAttackChoice;

public class RadialSpellActionCalculator extends EnemyActionCalculator
{
    private int radius;

    public RadialSpellActionCalculator(int radius)
    {
        this.radius = radius;
    }

    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new SummonAttackChoice(),
            new ConfusionChoice(),
            new RadialSpellChoice(0, this.radius),
            new MovementChoice()
        };
    }
}
