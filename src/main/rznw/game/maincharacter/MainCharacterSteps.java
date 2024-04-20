package rznw.game.maincharacter;

import rznw.game.skill.Skill;
import rznw.game.stat.Stat;

public class MainCharacterSteps
{
    private int HPSteps = 0;
    private int MPSteps = 0;
    private int manaRiverSteps = 0;

    private MainCharacter mainCharacter;

    public MainCharacterSteps(MainCharacter mainCharacter)
    {
        this.mainCharacter = mainCharacter;
    }

    public int getHPSteps()
    {
        return this.HPSteps;
    }

    public void setHPSteps(int HPSteps)
    {
        this.HPSteps = HPSteps;
    }

    public int getMPSteps()
    {
        return this.MPSteps;
    }

    public void setMPSteps(int MPSteps)
    {
        this.MPSteps = MPSteps;
    }

    public int getManaRiverSteps()
    {
        return this.manaRiverSteps;
    }

    public void setManaRiverSteps(int manaRiverSteps)
    {
        this.manaRiverSteps = manaRiverSteps;
    }

    public int getStepsForHeal()
    {
        return Math.max(1, 20 - this.mainCharacter.getStats().getStatPoints(Stat.STAT_PHYSICAL_REGENERATION));
    }

    public int getStepsForMPHeal()
    {
        return Math.max(1, 20 - this.mainCharacter.getStats().getStatPoints(Stat.STAT_MENTAL_REGENERATION));
    }

    public int getStepsForManaRiver()
    {
        return Math.max(1, 20 - this.mainCharacter.getSkills().getSkillPoints(Skill.SKILL_MANA_RIVER));
    }

    public void incrementSteps()
    {
        new MainCharacterStepIncrementer().incrementSteps(this.mainCharacter);
    }
}
