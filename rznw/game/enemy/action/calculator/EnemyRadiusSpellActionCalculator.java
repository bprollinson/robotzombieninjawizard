package rznw.game.enemy.action.calculator;

import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.RadialBasedHealAnySpellChoice;
import rznw.game.enemy.action.choice.MovementChoice;

public class EnemyRadiusSpellActionCalculator extends EnemyActionCalculator
{
    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new ConfusionChoice(),
            new RadialBasedHealAnySpellChoice(),
            new MovementChoice()
        };
    }
}
