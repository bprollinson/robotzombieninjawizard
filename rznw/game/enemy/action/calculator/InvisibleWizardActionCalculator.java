package rznw.game.enemy.action.calculator;

import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.InvisibleRadialSpellChoice;
import rznw.game.enemy.action.choice.MovementChoice;

public class InvisibleWizardActionCalculator extends EnemyActionCalculator
{
    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new ConfusionChoice(),
            new InvisibleRadialSpellChoice(2),
            new MovementChoice()
        };
    }
}
