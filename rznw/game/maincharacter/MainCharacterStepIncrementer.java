package rznw.game.maincharacter;

import rznw.game.maincharacter.inventory.Armor;
import rznw.game.maincharacter.inventory.Weapon;
import rznw.game.skill.Skill;
import rznw.game.stat.Stat;
import rznw.utility.RandomNumberGenerator;

public class MainCharacterStepIncrementer
{
    public void incrementSteps(MainCharacter mainCharacter)
    {
        if (mainCharacter.getStats().getStatPoints(Stat.STAT_PHYSICAL_REGENERATION) > 0)
        {
            mainCharacter.getSteps().setHPSteps(mainCharacter.getSteps().getHPSteps() + 1);
            if (mainCharacter.getSteps().getHPSteps() >= mainCharacter.getSteps().getStepsForHeal())
            {
                mainCharacter.heal(10);
                mainCharacter.getSteps().setHPSteps(0);
            }
        }

        if (mainCharacter.getStats().getStatPoints(Stat.STAT_MENTAL_REGENERATION) > 0)
        {
            mainCharacter.getSteps().setMPSteps(mainCharacter.getSteps().getMPSteps() + 1);
            if (mainCharacter.getSteps().getMPSteps() >= mainCharacter.getSteps().getStepsForMPHeal())
            {
                mainCharacter.healMP(10);
                mainCharacter.getSteps().setMPSteps(0);
            }
        }

        if (mainCharacter.getSkills().getSkillPoints(Skill.SKILL_MANA_RIVER) > 0)
        {
            mainCharacter.getSteps().setManaRiverSteps(mainCharacter.getSteps().getManaRiverSteps() + 1);
            if (mainCharacter.getSteps().getManaRiverSteps() >= mainCharacter.getSteps().getStepsForManaRiver())
            {
                int manaRiverProbability = mainCharacter.getSkills().getSkillPoints(Skill.SKILL_MANA_RIVER);

                if (RandomNumberGenerator.rollSucceeds(manaRiverProbability))
                {
                    mainCharacter.setMP(mainCharacter.getMaxMP());
                }

                mainCharacter.getSteps().setManaRiverSteps(0);
            }
        }

        Weapon weapon = mainCharacter.getEquipment().getEquippedWeapon();
        if (weapon != null)
        {
            weapon.step(mainCharacter);
        }

        Armor armor = mainCharacter.getEquipment().getEquippedArmor();
        if (armor != null)
        {
            armor.step(mainCharacter);
        }
    }
}
