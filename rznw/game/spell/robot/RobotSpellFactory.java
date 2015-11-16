package rznw.game.spell.robot;

import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;

public class RobotSpellFactory extends SpellFactory
{
    public Spell getSpell(int spellIndex)
    {
        switch (spellIndex)
        {
            case 2:
                return new OverloadSpell();
            case 3:
                return new SuckPowerSpell();
            case 4:
                return new RocketPackSpell();
            case 6:
                return new RocketShotSpell();
            case 7:
                return new ParalyzingBlastSpell();
            default:
                return null;
        }
    }
}
