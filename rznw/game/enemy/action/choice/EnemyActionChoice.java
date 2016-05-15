package rznw.game.enemy.action.choice;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.action.EnemyAction;
import rznw.map.GameWorld;

public interface EnemyActionChoice
{
    public abstract EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter);
}
