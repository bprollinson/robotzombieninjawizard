package rznw.game.enemy.action.choice;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyCharacterWithSpell;
import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.spell.EnemySpell;
import rznw.map.GameWorld;

public abstract class EnemyActionChoice
{
    public abstract EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter);

    protected boolean canCastSpell(EnemyCharacter enemyCharacter, int spellIndex)
    {
        int spellPoints = ((EnemyCharacterWithSpell)enemyCharacter).getSpellPoints(spellIndex);

        if (spellPoints == 0)
        {
            System.out.println("Not enough spell points");
            return false;
        }

        EnemySpell enemySpell = ((EnemyCharacterWithSpell)enemyCharacter).getSpell(spellIndex);
        int MPCost = enemySpell.getMPCost(spellPoints);

        if (MPCost > enemyCharacter.getMP())
        {
            System.out.println("Not enough MP");
            return false;
        }

        return true;
    }
}
