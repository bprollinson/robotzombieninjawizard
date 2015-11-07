package rznw.game.spell.wizard;

import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;

public class WizardSpellFactory extends SpellFactory
{
    public Spell getSpell(int spellIndex)
    {
        switch (spellIndex)
        {
            case 2:
                return new EarthquakeSpell();
            case 13:
                return new HealSpell();
            default:
                return null;
        }
    }
}
