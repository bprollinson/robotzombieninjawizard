package rznw.game.spell.zombie;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.map.GameWorld;

public class InferZombieSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Infer Zombie";
    }

    public String getDescription()
    {
        return "The next enemy killed by you or one of your zombies will turn into a zombie. This zombie will independently battle enemies in melee combat. It will continue to battle until its HP are exhausted.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Infer Zombie");

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_INFER_ZOMBIE, true);
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int maxHP = 200 + 10 * spellPoints;

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Zombie HP: " + maxHP,
            "Zombie damage: 10"
        };
    }
}
