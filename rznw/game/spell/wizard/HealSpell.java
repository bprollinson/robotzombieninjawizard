package rznw.game.spell.wizard;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;

public class HealSpell extends Spell
{
    public boolean canCast(MainCharacter character)
    {
        return character.getSpellPoints(13) > 0 && character.getMP() >= this.getMPCost(character);
    }

    public void cast(GameWorld gameWorld)
    {
        System.out.println("Casting Heal");
        MainCharacter character = gameWorld.getMainCharacter();
        character.heal(10 * character.getSpellPoints(13));
    }

    public int getMPCost(MainCharacter character)
    {
        int spellLevel = character.getSpellPoints(13);
        return Math.max(200 - 10 * spellLevel, 1);
    }
}
