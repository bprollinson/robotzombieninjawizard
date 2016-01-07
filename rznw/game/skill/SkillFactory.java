package rznw.game.skill;

public class SkillFactory
{
    public Skill getSkill(int skillIndex)
    {
        switch (skillIndex)
        {
            case 0:
                return new DetectVitalitySkill();
            case 1:
                return new DetectEnemiesSkill();
            case 2:
                return new BloodRageSkill();
            case 3:
                return new PotionFindSkill();
            case 5:
                return new WaypointSkill();
            case 6:
                return new FastHandsSkill();
            case 7:
                return new FindStairsSkill();
            case 8:
                return new ItemTradeSkill();
            case 9:
                return new RageSkill();
            case 10:
                return new AbundanceSkill();
            case 11:
                return new DisarmTrapsSkill();
            case 12:
                return new MagicSeedsSkill();
            case 13:
                return new ManaSuckSkill();
            case 15:
                return new ManaRiverSkill();
            default:
                return null;
        }
    }
}
