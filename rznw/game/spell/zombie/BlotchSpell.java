package rznw.game.spell.zombie;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.element.Blotch;
import rznw.map.element.MapElement;

public class BlotchSpell extends Spell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Blotch");

        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();
        Blotch blotch = new Blotch(characterElement.getRow(), characterElement.getColumn());
        gameWorld.getMap().setBackgroundElement(characterElement.getRow(), characterElement.getColumn(), blotch);
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}
