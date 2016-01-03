package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class MagicSeedsSkill extends PassiveSkill
{
    public String[] getStats(MainCharacter character, int skillPoints)
    {
        int bonusSpellLevels = (int)Math.floor(skillPoints / 4);

        return new String[] {
            "Bonus spell levels: " + bonusSpellLevels
        };
    }
}
