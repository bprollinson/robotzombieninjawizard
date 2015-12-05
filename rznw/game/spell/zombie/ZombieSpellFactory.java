package rznw.game.spell.zombie;

import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;

public class ZombieSpellFactory extends SpellFactory
{
    public Spell getSpell(int spellIndex)
    {
        switch (spellIndex)
        {
            case 0:
                return new PoisonSkinSpell();
            case 1:
                return new ResistDamageSpell();
            case 2:
                return new ThornSkinSpell();
            case 3:
                return new BarbedSkinSpell();
            case 4:
                return new FeedFleshSpell();
            case 6:
                return new FeedPastSpell();
            case 7:
                return new FeedMindSpell();
            case 8:
                return new LocustSwarmSpell();
            case 9:
                return new PoisonCloudSpell();
            case 11:
                return new BlotchSpell();
            default:
                return null;
        }
    }
}
