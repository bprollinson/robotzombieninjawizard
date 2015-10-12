package rznw.game.spell.wizard;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;

public class HealSpell extends Spell
{
    public boolean canCast(MainCharacter character)
    {
        return character.getSpellPoints(13) > 0 && character.getMP() >= this.getMPCost(character);
    }

    public void cast(MainCharacter character)
    {
        System.out.println("Casting Heal");
        character.setMP(character.getMP() - this.getMPCost(character));
        character.heal(character.getSpellPoints(13));
    }

    private int getMPCost(MainCharacter character)
    {
        int spellLevel = character.getSpellPoints(13);
        return Math.max(20 - spellLevel, 1);
    }
}
