package rznw.game.enemy.action;

import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.MovementChoice;
import rznw.game.enemy.action.choice.RadialSpellChoice;

public class BeastSummonerActionCalculator extends GenericEnemyActionCalculator
{
    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new ConfusionChoice(),
            new RadialSpellChoice(2),
            new MovementChoice()
        };
    }
}
