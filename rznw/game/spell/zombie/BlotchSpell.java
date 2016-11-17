package rznw.game.spell.zombie;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.element.Blotch;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;

public class BlotchSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Blotch";
    }

    public String getDescription()
    {
        return "Leaves a poison spot on the ground. Enemies who come into contact with this spot will become poisoned.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting blotch.");

        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();
        Blotch blotch = new Blotch(characterElement.getRow(), characterElement.getColumn());
        gameWorld.getMap().setBackgroundElement(characterElement.getRow(), characterElement.getColumn(), blotch);
        LogRendererFactory.instance().log("Blotch created.");
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Poison damage: 10 per turn"
        };
    }
}
