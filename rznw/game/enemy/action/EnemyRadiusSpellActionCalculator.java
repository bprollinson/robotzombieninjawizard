package rznw.game.enemy.action;

import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.HealSpellChoice;
import rznw.game.enemy.action.choice.MovementChoice;

public class EnemyRadiusSpellActionCalculator extends GenericEnemyActionCalculator
{
    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new ConfusionChoice(),
            new HealSpellChoice(),
            new MovementChoice()
        };
    }
}
