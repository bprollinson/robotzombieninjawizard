package rznw.game.enemy.action;

import rznw.game.enemy.Zenith;
import rznw.game.enemy.action.choice.ConfusionChoice;
import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.action.choice.MovementChoice;
import rznw.game.enemy.action.choice.ProjectileSpellChoice;
import rznw.game.enemy.action.choice.RadialHealthBasedSpellChoice;
import rznw.game.enemy.action.choice.ZenithHealSpellChoice;

public class ZenithActionCalculator extends GenericEnemyActionCalculator
{
    private static final int RADIUS = 2;

    public EnemyActionChoice[] getChoiceList()
    {
        return new EnemyActionChoice[] {
            new ConfusionChoice(),
            new ZenithHealSpellChoice(Zenith.SPELL_HEAL),
            new RadialHealthBasedSpellChoice(Zenith.SPELL_HEALTH_ZAP, ZenithActionCalculator.RADIUS),
            new ProjectileSpellChoice(Zenith.SPELL_ZAP),
            new MovementChoice()
        };
    }
}
