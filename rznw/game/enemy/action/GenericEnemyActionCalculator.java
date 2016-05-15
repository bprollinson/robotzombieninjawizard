package rznw.game.enemy.action;

import rznw.game.enemy.action.choice.EnemyActionChoice;
import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;

public abstract class GenericEnemyActionCalculator extends EnemyActionCalculator
{
    public abstract EnemyActionChoice[] getChoiceList();

    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        EnemyActionChoice[] choiceList = this.getChoiceList();

        for (int i = 0; i < choiceList.length; i++)
        {
            EnemyAction action = choiceList[i].getAction(gameWorld, enemyCharacter);

            if (action != null)
            {
                return action;
            }
        }

        return null;
    }
}
