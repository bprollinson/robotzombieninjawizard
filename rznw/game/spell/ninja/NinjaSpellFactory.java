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
            case 1:
                return new RoundhouseStrikeSpell();
            case 2:
                return new PoisonStrikeSpell();
            case 4:
                return new PinStrikeSpell();
            case 5:
                return new ShurikenStarSpell();
            case 6:
                return new SmokeClusterSpell();
            case 7:
                return new StunBombSpell();
            case 8:
                return new StealGoldSpell();
            case 11:
                return new StealExperienceSpell();
            case 12:
                return new SmokeBombSpell();
            case 13:
                return new CounterstrikeSpell();
            case 14:
                return new ReversePainSpell();
            case 15:
                return new DeathStrikeSpell();
            default:
                return null;
        }
    }
}
