package rznw.game.statuseffects;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Armor;
import rznw.game.stat.Stat;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class StatusEffectDodgeCalculator
{
    public boolean characterDodgesEffect(Character character)
    {
        if (!character.isMainCharacter())
        {
            return false;
        }

        int statPoints = ((MainCharacter)character).getStats().getStatPoints(Stat.STAT_THICK_SKIN);
        int probability = 5 * statPoints;

        Armor armor = ((MainCharacter)character).getEquipment().getEquippedArmor();
        if (armor != null)
        {
            probability += armor.getThickSkinBonus();
        }

        boolean success = RandomNumberGenerator.rollSucceeds(probability);

        if (success)
        {
            LogRendererFactory.instance().log("You dodged a status effect via thick skin.");
        }

        return success;
    }
}
