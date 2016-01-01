package rznw.game.spell.wizard;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

public class UpdraftSpell extends UndirectedSpell
{
    public boolean canCast(GameWorld gameWorld, int spellPoints)
    {
        if (!super.canCast(gameWorld, spellPoints))
        {
            return false;
        }

        return gameWorld.getMap().getLevel() > 1;
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Updraft");

        gameWorld.generatePreviousMap();
        Map map = gameWorld.getMap();
        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();
        map.setElementVisited(gameWorld.getMainCharacter(), characterElement.getRow(), characterElement.getColumn());
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints)
        };
    }
}
