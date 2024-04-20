package rznw.game.spell.wizard;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;

public class UpdraftSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Updraft";
    }

    public String getDescription()
    {
        return "Allows you to travel back up to the previous dungeon level. This regenerates a random level of difficulty one less than the current level. Can't be used on the first dungeon level.";
    }

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
        LogRendererFactory.instance().log("Casting updraft.");

        gameWorld.generatePreviousMap();
        Map map = gameWorld.getMap();
        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();
        map.setElementVisited(gameWorld.getMainCharacter(), characterElement.getRow(), characterElement.getColumn());
        gameWorld.getMainCharacter().getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_REGENERATE_SHOP, true);

        LogRendererFactory.instance().log("Welcome to dungeon level " + map.getLevel() + "!");
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
