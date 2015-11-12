package rznw.game.spell.wizard;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;

public class HeatRaySpell extends Spell
{
    public boolean canCast(MainCharacter character)
    {
        return character.getSpellPoints(6) > 0 && character.getMP() >= this.getMPCost(character);
    }

    public void cast(GameWorld gameWorld)
    {
    }

    public void cast(GameWorld gameWorld, int direction)
    {
        System.out.println("Casting Heat Ray");
    }

    private int getMPCost(MainCharacter character)
    {
        int spellLevel = character.getSpellPoints(6);
        return Math.max(200 - 10 * spellLevel, 1);
    }

    public boolean requiresDirectionInput()
    {
        return true;
    }
}
