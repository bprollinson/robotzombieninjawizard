package rznw.game.maincharacter;

import rznw.game.skill.SkillFactory;

public class MainCharacterSkills
{
    public static final int SKILL_POINTS_PER_LEVEL = 4;
    public static final int MAX_SKILL_POINTS = 20;

    private static String[] skillCategory = {
        "Vitality",
        "Agility",
        "Fortitude",
        "Magic"
    };

    private int[] skills;

    public MainCharacterSkills()
    {
        this.skills = new int[16];

        for (int i = 0; i < this.skills.length; i++)
        {
            this.skills[i] = 0;
        }
    }

    public static String getSkillCategory(int categoryNumber)
    {
        return MainCharacterSkills.skillCategory[categoryNumber];
    }

    public static String getSkillName(int skillNumber)
    {
        return MainCharacterSkills.getSkillFactory().getSkill(skillNumber).getDisplayName();
    }

    public static String getSkillDescription(int skillNumber)
    {
        return MainCharacterSkills.getSkillFactory().getSkill(skillNumber).getDescription();
    }

    public void addSkillPoint(int skillNumber)
    {
        this.skills[skillNumber]++;
    }

    public int getSkillPoints(int skillNumber)
    {
        return this.skills[skillNumber];
    }

    public void setSkillPoints(int skillNumber, int points)
    {
        this.skills[skillNumber] = points;
    }

    public static SkillFactory getSkillFactory()
    {
        return new SkillFactory();
    }
}
