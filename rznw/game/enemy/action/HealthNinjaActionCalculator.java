package rznw.game.enemy.action;

import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.DamagedEscapeChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.MovementChoice;
import rznw.game.enemy.action.choice.PartialHealSpellChoice;

public class HealthNinjaActionCalculator extends EnemyActionCalculator
{
    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new ConfusionChoice(),
            new DamagedEscapeChoice(2),
            new PartialHealSpellChoice(0),
            new MovementChoice()
        };
    }
}
