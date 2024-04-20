package rznw.game.spell.wizard;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class HealSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Heal";
    }

    public String getDescription()
    {
        return "Replenishes lost HP. Healing increases as spell level increases.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting heal.");
        MainCharacter character = gameWorld.getMainCharacter();
        int HPHealed = character.heal(10 * spellPoints);
        LogRendererFactory.instance().log("You healed " + HPHealed + " HP.");
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "HP healed: " + 10 * spellPoints
        };
    }
}
