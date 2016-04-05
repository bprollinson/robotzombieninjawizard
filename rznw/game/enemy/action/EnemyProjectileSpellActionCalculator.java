package rznw.game.enemy.action;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyCharacterWithSpell;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;

public class EnemyProjectileSpellActionCalculator extends EnemyActionCalculator
{
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

        int deltaRow = 0;
        int deltaColumn = 0;

        if (mainCharacterMapElement.getRow() != enemyMapElement.getRow())
        {
            deltaRow = mainCharacterMapElement.getRow() < enemyMapElement.getRow() ? -1 : 1;
        }

        if (mainCharacterMapElement.getColumn() != enemyMapElement.getColumn())
        {
            deltaColumn = mainCharacterMapElement.getColumn() < enemyMapElement.getColumn() ? -1 : 1;
        }

        if (deltaRow != 0 && deltaColumn != 0)
        {
            return null;
        }

        Map map = gameWorld.getMap();
        boolean obstacleFound = false;

        int row = enemyMapElement.getRow() + deltaRow;
        int column = enemyMapElement.getColumn() + deltaColumn;
        int targetRow = mainCharacterMapElement.getRow();
        int targetColumn = mainCharacterMapElement.getColumn();

        while (row != targetRow || column != targetColumn)
        {
            if (map.getElement(row, column) != null)
            {
                obstacleFound = true;
                break;
            }

            row += deltaRow;
            column += deltaColumn;
        }

        if (obstacleFound)
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
