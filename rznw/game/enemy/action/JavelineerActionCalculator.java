package rznw.game.enemy.action;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyCharacterWithSpell;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;

public class JavelineerActionCalculator extends EnemyActionCalculator
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
            deltaRow = Math.abs(mainCharacterMapElement.getRow() - enemyMapElement.getRow());
        }

        if (mainCharacterMapElement.getColumn() != enemyMapElement.getColumn())
        {
            deltaColumn = Math.abs(mainCharacterMapElement.getColumn() - enemyMapElement.getColumn());
        }

        if (deltaRow != 1 || deltaColumn != 1)
        {
            System.out.println("Positioning not right");
            return null;
        }

        int spellPoints = ((EnemyCharacterWithSpell)enemyCharacter).getSpellPoints(0);

        if (spellPoints == 0)
        {
            System.out.println("Not enough spell points");
            return null;
        }

        EnemySpell enemySpell = ((EnemyCharacterWithSpell)enemyCharacter).getSpell(0);
        int MPCost = enemySpell.getMPCost(spellPoints);

        if (MPCost > enemyCharacter.getMP())
        {
            System.out.println("Not enough MP");
            return null;
        }

        return new EnemySpellAction(((EnemyCharacterWithSpell)enemyCharacter).getSpell(0), spellPoints);
    }
}
