package rznw.turn;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;
import rznw.utility.StringUtils;

import java.util.Collection;
import java.util.Iterator;

public class EnemyTurnHandler implements TurnFragmentHandler
{
    private GameWorld gameWorld;

    public EnemyTurnHandler(GameWorld gameWorld)
    {
        this.gameWorld = gameWorld;
    }

    public void handleTurnFragment()
    {
        new SummonTurnHandler(this.gameWorld).handleTurnFragment();

        MainCharacter character = this.gameWorld.getMainCharacter();

        Collection<EnemyCharacter> enemies = this.gameWorld.getMap().getEnemies();
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            if (enemy.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_FROZEN) > 0)
            {
                StringUtils utils = new StringUtils();
                LogRendererFactory.instance().log(utils.UCFirst(enemy.getLogName()) + " is incapacitated turing its turn.");
            }
            else
            {
                EnemyAction enemyAction = enemy.getAction(this.gameWorld);

                if (enemyAction.isPositionChange())
                {
                    new CharacterTurnHandler(this.gameWorld).handleTurnFragment(enemyAction.getPositionChange(), enemy);
                }

                if (enemyAction.isSpell())
                {
                    EnemySpell spell = enemyAction.getSpell();
                    spell.cast(this.gameWorld, enemy, enemyAction.getSpellPoints());

                    int MPCost = spell.getMPCost(enemyAction.getSpellPoints());
                    enemy.setMP(enemy.getMP() - MPCost);
                }
            }

            Map map = this.gameWorld.getMap();
            MapElement enemyMapElement = enemy.getMapElement();
            MapElement backgroundElement = map.getBackgroundElement(enemyMapElement.getRow(), enemyMapElement.getColumn());

            if (backgroundElement != null)
            {
                backgroundElement.collideWithEnemy(this.gameWorld, enemy);
            }

            enemy.getStatusEffects().processTurn(this.gameWorld);
        }

        if (character.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_SKIP) > 0 || character.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_FROZEN) > 0)
        {
            LogRendererFactory.instance().log("You are incapacitated during your turn.");
            character.getStatusEffects().processTurn(this.gameWorld);
            this.handleTurnFragment();
        }
    }
}
