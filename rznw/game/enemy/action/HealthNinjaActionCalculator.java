package rznw.game.enemy.action;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyCharacterWithSpell;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;

public class HealthNinjaActionCalculator extends EnemyActionCalculator
{
    private static final int RADIUS = 2;

    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        EnemyAction confusionPositionChange = this.getConfusionPositionChange(gameWorld, enemyCharacter);
        if (confusionPositionChange != null)
        {
            return confusionPositionChange;
        }

        EnemyAction spellAction = this.getSpellAction(gameWorld, enemyCharacter);
        if (spellAction != null)
        {
            return spellAction;
        }

        return this.getPathBasedPositionChange(gameWorld, enemyCharacter);
    }

    private EnemyAction getSpellAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        System.out.println("In getSpellAction");

        MainCharacter mainCharacter = gameWorld.getMainCharacter();

        MapElement mainCharacterMapElement = mainCharacter.getMapElement();
        MapElement enemyMapElement = enemyCharacter.getMapElement();

        if (enemyCharacter.getHP() > Math.floor(0.6 * enemyCharacter.getMaxHP()))
        {
            System.out.println("Too healthy - just attacking");
            return null;
        }

        if (Math.abs(mainCharacterMapElement.getRow() - enemyMapElement.getRow()) < HealthNinjaActionCalculator.RADIUS && Math.abs(mainCharacterMapElement.getColumn() - enemyMapElement.getColumn()) < HealthNinjaActionCalculator.RADIUS)
        {
            System.out.println("Damaged and close, move away");
            System.out.println("Difference 1: " + Math.abs(mainCharacterMapElement.getRow() - enemyMapElement.getRow()));
            System.out.println("Difference 2: " + Math.abs(mainCharacterMapElement.getColumn() - enemyMapElement.getColumn()));

            int deltaRow = 0;
            if (mainCharacterMapElement.getRow() < enemyMapElement.getRow())
            {
                deltaRow = 1;
            }
            if (mainCharacterMapElement.getRow() > enemyMapElement.getRow())
            {
                deltaRow = -1;
            }

            int deltaColumn = 0;
            if (mainCharacterMapElement.getColumn() < enemyMapElement.getColumn())
            {
                deltaColumn = 1;
            }
            if (mainCharacterMapElement.getColumn() > enemyMapElement.getColumn())
            {
                deltaColumn = -1;
            }

            return new EnemyMovementAction(new EnemyAIBasedPositionChange(enemyCharacter, deltaRow, deltaColumn));
        }

        System.out.println("Healing!");

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
