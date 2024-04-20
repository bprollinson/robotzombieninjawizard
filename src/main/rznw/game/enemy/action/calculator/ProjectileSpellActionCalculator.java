package rznw.game.enemy.action.calculator;

import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.MovementChoice;
import rznw.game.enemy.action.choice.ProjectileSpellChoice;
import rznw.game.enemy.action.choice.SummonAttackChoice;

public class ProjectileSpellActionCalculator extends EnemyActionCalculator
{
    private static final int SPELL_INDEX = 0;

    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new SummonAttackChoice(),
            new ConfusionChoice(),
            new ProjectileSpellChoice(ProjectileSpellActionCalculator.SPELL_INDEX),
            new MovementChoice()
        };
    }
}
