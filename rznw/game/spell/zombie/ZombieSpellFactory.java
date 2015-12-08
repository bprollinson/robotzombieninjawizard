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
            case 5:
                return new FeedBrainSpell();
            case 6:
                return new FeedPastSpell();
            case 7:
                return new FeedMindSpell();
            case 8:
                return new LocustSwarmSpell();
            case 9:
                return new PoisonCloudSpell();
            case 10:
                return new InfectiousRageSpell();
            case 11:
                return new BlotchSpell();
            case 12:
                return new SummonZombieSpell();
            case 13:
                return new InferZombieSpell();
            case 14:
                return new MultiplyZombiesSpell();
            case 15:
                return new ExplodeZombiesSpell();
            default:
                return null;
        }
    }
}
