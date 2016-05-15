package rznw.game.enemy.action;

import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.MovementChoice;
import rznw.game.enemy.action.choice.ProjectileSpellChoice;

public class EnemyProjectileSpellActionCalculator extends GenericEnemyActionCalculator
{
    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new ConfusionChoice(),
            new ProjectileSpellChoice(),
            new MovementChoice()
        };
    }
}
