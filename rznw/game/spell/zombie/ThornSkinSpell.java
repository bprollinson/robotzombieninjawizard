package rznw.game.spell.zombie;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;

public class ThornSkinSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Thorn Skin";
    }

    public String getDescription()
    {
        return "Enemies take damage when physically attacking you for a period of time. This does not affect ranged attacks.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Thorn Skin");

        MainCharacter character = gameWorld.getMainCharacter();
        int numTurns = 1 + (int)Math.floor(spellPoints / 4);

        character.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_THORN_SKIN, numTurns);
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int numTurns = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Number of turns: " + numTurns,
            "Damage: " + 5 * spellPoints
        };
    }
}
