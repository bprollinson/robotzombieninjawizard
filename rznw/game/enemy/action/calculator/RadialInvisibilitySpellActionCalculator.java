package rznw.game.enemy.action.calculator;

import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.MovementChoice;
import rznw.game.enemy.action.choice.RadialInvisibilitySpellChoice;

public class RadialInvisibilitySpellActionCalculator extends EnemyActionCalculator
{
    private static final int SPELL_INDEX = 0;
    private static final int RADIUS = 2;

    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new ConfusionChoice(),
            new RadialInvisibilitySpellChoice(RadialInvisibilitySpellActionCalculator.SPELL_INDEX, RadialInvisibilitySpellActionCalculator.RADIUS),
            new MovementChoice()
        };
    }
}
