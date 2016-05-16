package rznw.game.enemy.action;

import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.MovementChoice;
import rznw.game.enemy.action.choice.RadialPoisonSpellChoice;

public class FumeBeastActionCalculator extends GenericEnemyActionCalculator
{
    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new ConfusionChoice(),
            new RadialPoisonSpellChoice(),
            new MovementChoice()
        };
    }
}
