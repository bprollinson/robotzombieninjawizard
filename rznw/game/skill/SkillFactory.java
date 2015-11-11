package rznw.game.skill;

public class SkillFactory
{
    public Skill getSkill(int skillIndex)
    {
        switch (skillIndex)
        {
            case 1:
                return new DetectEnemiesSkill();
            case 5:
                return new WaypointSkill();
            case 7:
                return new FindStairsSkill();
            default:
                return null;
        }
    }
}
