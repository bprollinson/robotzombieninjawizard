package rznw.game.spell.ninja;

import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;

public class NinjaSpellFactory extends SpellFactory
{
    public Spell getSpell(int spellIndex)
    {
        switch (spellIndex)
        {
            case 0:
                return new StunStrikeSpell();
            case 5:
                return new ShurikenStarSpell();
            default:
                return null;
        }
    }
}
