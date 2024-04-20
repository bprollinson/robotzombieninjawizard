package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.game.statuseffects.StatusEffectStats;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;

import java.io.BufferedWriter;

public class MainCharacterSaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        MainCharacter mainCharacter = gameWorld.getMainCharacter();

        this.writeLine(fileWriter, mainCharacter.getCharacterClassNumber());
        this.writeLine(fileWriter, mainCharacter.getExperience().getLevel());
        this.writeLine(fileWriter, mainCharacter.getExperience().getExperience());

        this.writeLine(fileWriter, mainCharacter.getHP());
        this.writeLine(fileWriter, mainCharacter.getMP());

        this.writeLine(fileWriter, mainCharacter.getSteps().getHPSteps());
        this.writeLine(fileWriter, mainCharacter.getSteps().getMPSteps());
        this.writeLine(fileWriter, mainCharacter.getSteps().getManaRiverSteps());

        for (int i = 0; i < 16; i++)
        {
            this.writeLine(fileWriter, mainCharacter.getStats().getStatPoints(i));
        }

        for (int i = 0; i < 16; i++)
        {
            this.writeLine(fileWriter, mainCharacter.getSkills().getSkillPoints(i));
        }

        for (int i = 0; i < 16; i++)
        {
            this.writeLine(fileWriter, mainCharacter.getSpells().getSpellPoints(i, false));
        }

        for (int i = 0; i < SimpleStatusEffects.NUM_STATUS_EFFECTS; i++)
        {
            this.writeLine(fileWriter, mainCharacter.getStatusEffects().getStatusEffect(i) ? 1 : 0);
        }

        for (int i = 0; i < TurnBasedStatusEffects.NUM_STATUS_EFFECTS; i++)
        {
            this.writeLine(fileWriter, mainCharacter.getStatusEffects().getStatusEffectTurns(i));
        }

        for (int i = 0; i < StatusEffectStats.NUM_STATS; i++)
        {
            this.writeLine(fileWriter, mainCharacter.getStatusEffects().getStat(i));
        }
    }
}
