package rznw.turn;

import rznw.game.SummonedCharacter;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;

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
        this.handleSummonedZombieTurns();

        MainCharacter character = this.gameWorld.getMainCharacter();

        Collection<EnemyCharacter> enemies = this.gameWorld.getMap().getEnemies();
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            if (enemy.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_FROZEN) > 0)
            {
                System.out.println("Enemy is frozen!");
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
                    System.out.println("Enemy is casting a spell");

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
            System.out.println("Enemies take a turn while you are sleeping / frozen");
            character.getStatusEffects().processTurn(this.gameWorld);
            this.handleTurnFragment();
        }
    }

    private void handleSummonedZombieTurns()
    {
        System.out.println("Handling summon turns");

        MainCharacter character = this.gameWorld.getMainCharacter();

        Collection<SummonedCharacter> summons = this.gameWorld.getMap().getSummons();
        for (Iterator iterator = summons.iterator(); iterator.hasNext();)
        {
            System.out.println("Have a summon");

            SummonedCharacter summon = (SummonedCharacter)iterator.next();
            EnemyAIBasedPositionChange summonPositionChange = summon.getPositionChange(this.gameWorld);
            System.out.println("Position change: " + summonPositionChange.getInitialRow() + ", " + summonPositionChange.getInitialColumn() + " -> " + summonPositionChange.getFinalRow() + ", " + summonPositionChange.getFinalColumn());

            new CharacterTurnHandler(this.gameWorld).handleTurnFragment(summonPositionChange, summon);

            summon.getStatusEffects().processTurn(this.gameWorld);
        }
    }
}
