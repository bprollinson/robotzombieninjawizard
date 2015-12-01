package rznw.game.spell.wizard;

import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;

public class WizardSpellFactory extends SpellFactory
{
    public Spell getSpell(int spellIndex)
    {
        switch (spellIndex)
        {
            case 0:
                return new RockWallSpell();
            case 1:
                return new MeteorShowerSpell();
            case 2:
                return new EarthquakeSpell();
            case 4:
                return new RingOfFireSpell();
            case 5:
                return new FireballSpell();
            case 6:
                return new HeatRaySpell();
            case 7:
                return new ArcLightningSpell();
            case 8:
                return new RepelSpell();
            case 9:
                return new TeleportSpell();
            case 10:
                return new RicochetBlastSpell();
            case 11:
                return new UpdraftSpell();
            case 12:
                return new IceFieldSpell();
            case 13:
                return new HealSpell();
            case 15:
                return new VitalZapSpell();
            default:
                return null;
        }
    }
}
