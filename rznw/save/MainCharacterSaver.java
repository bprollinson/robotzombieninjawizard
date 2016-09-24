package rznw.save;

import rznw.game.StatusEffects;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

import java.io.BufferedWriter;

public class MainCharacterSaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        MainCharacter mainCharacter = gameWorld.getMainCharacter();

        this.writeLine(fileWriter, mainCharacter.getCharacterClassNumber());
        this.writeLine(fileWriter, mainCharacter.getLevel());
        this.writeLine(fileWriter, mainCharacter.getExperience());

        this.writeLine(fileWriter, mainCharacter.getHP());
        this.writeLine(fileWriter, mainCharacter.getMP());

        this.writeLine(fileWriter, mainCharacter.getHPSteps());
        this.writeLine(fileWriter, mainCharacter.getMPSteps());
        this.writeLine(fileWriter, mainCharacter.getManaRiverSteps());

        for (int i = 0; i < 16; i++)
        {
            this.writeLine(fileWriter, mainCharacter.getStatPoints(i));
        }

        for (int i = 0; i < 16; i++)
        {
            this.writeLine(fileWriter, mainCharacter.getSkillPoints(i));
        }

        for (int i = 0; i < 16; i++)
        {
            this.writeLine(fileWriter, mainCharacter.getSpellPoints(i, false));
        }

        for (int i = 0; i < StatusEffects.NUM_STATUS_EFFECTS; i++)
        {
            this.writeLine(fileWriter, mainCharacter.getStatusEffects().getStatusEffect(i) ? 1 : 0);
        }

        for (int i = 0; i < StatusEffects.NUM_STATUS_EFFECT_TURNS; i++)
        {
            this.writeLine(fileWriter, mainCharacter.getStatusEffects().getStatusEffectTurns(i));
        }

        for (int i = 0; i < StatusEffects.NUM_STATS; i++)
        {
            this.writeLine(fileWriter, mainCharacter.getStatusEffects().getStat(i));
        }
    }
}
