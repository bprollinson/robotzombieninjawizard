package rznw.game.enemy.action.choice;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.action.EnemyMovementAction;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;

public class DamagedEscapeChoice extends EnemyActionChoice
{
    private int radius;

    public DamagedEscapeChoice(int radius)
    {
        this.radius = radius;
    }

    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
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

        if (Math.abs(mainCharacterMapElement.getRow() - enemyMapElement.getRow()) < this.radius && Math.abs(mainCharacterMapElement.getColumn() - enemyMapElement.getColumn()) < this.radius)
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

        return null;
    }
}
