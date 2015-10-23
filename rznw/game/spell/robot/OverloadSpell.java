package rznw.game.spell.robot;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;

public class OverloadSpell extends Spell
{
    public boolean canCast(MainCharacter character)
    {
        return character.getSpellPoints(2) > 0 && character.getMP() >= this.getMPCost(character);
    }

    public void cast(GameWorld gameWorld)
    {
        System.out.println("Casting Overload");
        MainCharacter character = gameWorld.getMainCharacter();
        character.setMP(character.getMP() - this.getMPCost(character));
    }

    private int getMPCost(MainCharacter character)
    {
        int spellLevel = character.getSpellPoints(2);
        return Math.max(20 - spellLevel, 1);
    }
}
