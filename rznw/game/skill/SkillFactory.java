package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class SkillFactory
{
    public Skill getSkill(int skillIndex)
    {
        switch (skillIndex)
        {
            case MainCharacter.SKILL_DETECT_VITALITY:
                return new DetectVitalitySkill();
            case MainCharacter.SKILL_DETECT_ENEMIES:
                return new DetectEnemiesSkill();
            case MainCharacter.SKILL_BLOOD_RAGE:
                return new BloodRageSkill();
            case MainCharacter.SKILL_POTION_FIND:
                return new PotionFindSkill();
            case MainCharacter.SKILL_SUMMON_SHOPKEEPER:
                return new SummonShopkeeperSkill();
            case MainCharacter.SKILL_WAYPOINT:
                return new WaypointSkill();
            case MainCharacter.SKILL_FAST_HANDS:
                return new FastHandsSkill();
            case MainCharacter.SKILL_FIND_STAIRS:
                return new FindStairsSkill();
            case MainCharacter.SKILL_ITEM_TRADE:
                return new ItemTradeSkill();
            case MainCharacter.SKILL_RAGE:
                return new RageSkill();
            case MainCharacter.SKILL_ABUNDANCE:
                return new AbundanceSkill();
            case MainCharacter.SKILL_DISARM_TRAPS:
                return new DisarmTrapsSkill();
            case MainCharacter.SKILL_MAGIC_SEEDS:
                return new MagicSeedsSkill();
            case MainCharacter.SKILL_MANA_SUCK:
                return new ManaSuckSkill();
            case MainCharacter.SKILL_PROTECTIVE_FIELD:
                return new ProtectiveFieldSkill();
            case MainCharacter.SKILL_MANA_RIVER:
                return new ManaRiverSkill();
            default:
                return null;
        }
    }
}
