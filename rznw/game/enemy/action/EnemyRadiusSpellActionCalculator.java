package rznw.game.enemy.action;

import rznw.game.enemy.EnemyCharacterWithSpell;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;

public class EnemyRadiusSpellActionCalculator extends EnemyActionCalculator
{
    private static final int RADIUS = 2;

    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        EnemyAction confusionPositionChange = this.getConfusionPositionChange(gameWorld, enemyCharacter);
        if (confusionPositionChange != null)
        {
            return confusionPositionChange;
        }

        EnemySpellAction spellAction = this.getSpellAction(gameWorld, enemyCharacter);
        if (spellAction != null)
        {
            return spellAction;
        }

        return this.getPathBasedPositionChange(gameWorld, enemyCharacter);
    }

    private EnemySpellAction getSpellAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        System.out.println("In getSpellAction");

        MainCharacter mainCharacter = gameWorld.getMainCharacter();

        MapElement mainCharacterMapElement = mainCharacter.getMapElement();
        MapElement enemyMapElement = enemyCharacter.getMapElement();

        if (enemyCharacter.getHP() == enemyCharacter.getMaxHP())
        {
            return null;
        }

        if (Math.abs(mainCharacterMapElement.getRow() - enemyMapElement.getRow()) > EnemyRadiusSpellActionCalculator.RADIUS || Math.abs(mainCharacterMapElement.getColumn() - enemyMapElement.getColumn()) > EnemyRadiusSpellActionCalculator.RADIUS)
        {
            return null;
        }

        int spellPoints = ((EnemyCharacterWithSpell)enemyCharacter).getSpellPoints(0);

        if (spellPoints == 0)
        {
            return null;
        }

        EnemySpell enemySpell = ((EnemyCharacterWithSpell)enemyCharacter).getSpell(0);
        int MPCost = enemySpell.getMPCost(spellPoints);

        if (MPCost > enemyCharacter.getMP())
        {
            return null;
        }

        return new EnemySpellAction(((EnemyCharacterWithSpell)enemyCharacter).getSpell(0), spellPoints);
    }
}
