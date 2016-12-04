package rznw.game.enemy.spell;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.Werewolf;
import rznw.map.ClosestSquareCalculator;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;
import rznw.utility.StringUtils;

public class SummonBeastSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        StringUtils utils = new StringUtils();
        LogRendererFactory.instance().log(utils.UCFirst(enemyCharacter.getLogName()) + " casts summon beast.");

        MapElement element = this.getPositionElement(gameWorld, enemyCharacter);

        Werewolf werewolf = new Werewolf(spellPoints);
        werewolf.generateMapElement(element.getRow(), element.getColumn());
        gameWorld.getMap().setElement(werewolf.getMapElement().getRow(), werewolf.getMapElement().getColumn(), werewolf.getMapElement());
        LogRendererFactory.instance().log("Summoned a werewolf.");
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
