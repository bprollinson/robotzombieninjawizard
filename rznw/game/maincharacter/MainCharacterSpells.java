package rznw.game.maincharacter;

import rznw.game.skill.Skill;
import rznw.game.statuseffects.TurnBasedStatusEffects;

public class MainCharacterSpells
{
    public static final int SPELL_POINTS_PER_LEVEL = 4;
    public static final int MAX_SPELL_POINTS = 20;

    private MainCharacter mainCharacter;
    private int[] spells;

    public MainCharacterSpells(MainCharacter mainCharacter)
    {
        this.mainCharacter = mainCharacter;
        this.spells = new int[16];

        for (int i = 0; i < this.spells.length; i++)
        {
            this.spells[i] = 0;
        }
    }

    public String getSpellName(int spellNumber)
    {
        return this.mainCharacter.getSpellFactory().getSpell(spellNumber).getDisplayName();
    }

    public String getSpellDescription(int spellNumber)
    {
        return this.mainCharacter.getSpellFactory().getSpell(spellNumber).getDescription();
    }

    public void addSpellPoint(int spellNumber)
    {
        this.spells[spellNumber]++;
    }

    public int getSpellPoints(int spellNumber, boolean allowAdjustment)
    {
        if (!allowAdjustment)
        {
            return this.spells[spellNumber];
        }

        return this.getSpellPoints(spellNumber);
    }

    public int getSpellPoints(int spellNumber)
    {
        int spellPoints = this.spells[spellNumber];

        int bonusSpellPoints = 0;

        if (this.mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_MAGIC_SEEDS) > 0)
        {
            int magicSeedPoints = this.mainCharacter.getSkills().getSkillPoints(Skill.SKILL_MAGIC_SEEDS);
            bonusSpellPoints = (int)Math.floor(magicSeedPoints / 4);

            spellPoints += bonusSpellPoints;
        }

        return Math.min(spellPoints, MainCharacterSpells.MAX_SPELL_POINTS);
    }

    public void setSpellPoints(int spellNumber, int points)
    {
        this.spells[spellNumber] = points;
    }
}
