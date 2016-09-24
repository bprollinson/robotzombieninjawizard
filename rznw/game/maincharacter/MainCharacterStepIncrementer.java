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
        if (mainCharacter.getStatPoints(Stat.STAT_PHYSICAL_REGENERATION) > 0)
        {
            mainCharacter.setHPSteps(mainCharacter.getHPSteps() + 1);
            if (mainCharacter.getHPSteps() >= mainCharacter.getStepsForHeal())
            {
                mainCharacter.heal(10);
                mainCharacter.setHPSteps(0);
            }
        }

        if (mainCharacter.getStatPoints(Stat.STAT_MENTAL_REGENERATION) > 0)
        {
            mainCharacter.setMPSteps(mainCharacter.getMPSteps() + 1);
            if (mainCharacter.getMPSteps() >= mainCharacter.getStepsForMPHeal())
            {
                mainCharacter.healMP(10);
                mainCharacter.setMPSteps(0);
            }
        }

        if (mainCharacter.getSkillPoints(Skill.SKILL_MANA_RIVER) > 0)
        {
            mainCharacter.setManaRiverSteps(mainCharacter.getManaRiverSteps() + 1);
            if (mainCharacter.getManaRiverSteps() >= mainCharacter.getStepsForManaRiver())
            {
                int manaRiverProbability = mainCharacter.getSkillPoints(Skill.SKILL_MANA_RIVER);

                if (RandomNumberGenerator.rollSucceeds(manaRiverProbability))
                {
                    mainCharacter.setMP(mainCharacter.getMaxMP());
                }

                mainCharacter.setManaRiverSteps(0);
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
