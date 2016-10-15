package rznw.game.enemy.spell;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.Werewolf;
import rznw.map.ClosestSquareCalculator;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;

public class SummonBeastSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        System.out.println("Enemy is casting summon beast with spell points of: " + spellPoints);

        MapElement element = this.getPositionElement(gameWorld, enemyCharacter);
        System.out.println("Summoning werewolf at: " + element.getRow() + ", " + element.getColumn());

        Werewolf werewolf = new Werewolf(spellPoints);
        werewolf.generateMapElement(element.getRow(), element.getColumn());
        gameWorld.getMap().setElement(werewolf.getMapElement().getRow(), werewolf.getMapElement().getColumn(), werewolf.getMapElement());
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    private MapElement getPositionElement(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        return new ClosestSquareCalculator(gameWorld).getClosestPositionElement(enemyCharacter.getMapElement());
    }
}
