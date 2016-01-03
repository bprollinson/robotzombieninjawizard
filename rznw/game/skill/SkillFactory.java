package rznw.game.skill;

public class SkillFactory
{
    public Skill getSkill(int skillIndex)
    {
        switch (skillIndex)
        {
            case 1:
                return new DetectEnemiesSkill();
            case 2:
                return new BloodRageSkill();
            case 3:
                return new PotionFindSkill();
            case 5:
                return new WaypointSkill();
            case 7:
                return new FindStairsSkill();
            case 10:
                return new AbundanceSkill();
            case 11:
                return new DisarmTrapsSkill();
            case 13:
                return new ManaSuckSkill();
            case 15:
                return new ManaRiverSkill();
            default:
                return null;
        }
    }
}
