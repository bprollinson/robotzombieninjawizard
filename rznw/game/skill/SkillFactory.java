package rznw.game.skill;

public class SkillFactory
{
    public Skill getSkill(int skillIndex)
    {
        switch (skillIndex)
        {
            case Skill.SKILL_DETECT_VITALITY:
                return new DetectVitalitySkill();
            case Skill.SKILL_DETECT_ENEMIES:
                return new DetectEnemiesSkill();
            case Skill.SKILL_BLOOD_RAGE:
                return new BloodRageSkill();
            case Skill.SKILL_POTION_FIND:
                return new PotionFindSkill();
            case Skill.SKILL_SUMMON_SHOPKEEPER:
                return new SummonShopkeeperSkill();
            case Skill.SKILL_WAYPOINT:
                return new WaypointSkill();
            case Skill.SKILL_FAST_HANDS:
                return new FastHandsSkill();
            case Skill.SKILL_FIND_STAIRS:
                return new FindStairsSkill();
            case Skill.SKILL_ITEM_TRADE:
                return new ItemTradeSkill();
            case Skill.SKILL_RAGE:
                return new RageSkill();
            case Skill.SKILL_ABUNDANCE:
                return new AbundanceSkill();
            case Skill.SKILL_DISARM_TRAPS:
                return new DisarmTrapsSkill();
            case Skill.SKILL_MAGIC_SEEDS:
                return new MagicSeedsSkill();
            case Skill.SKILL_MANA_SUCK:
                return new ManaSuckSkill();
            case Skill.SKILL_PROTECTIVE_FIELD:
                return new ProtectiveFieldSkill();
            case Skill.SKILL_MANA_RIVER:
                return new ManaRiverSkill();
            default:
                return null;
        }
    }
}
