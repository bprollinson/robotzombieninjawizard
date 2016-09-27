package rznw.save;

import rznw.game.StatusEffects;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

import java.io.BufferedReader;

public class MainCharacterLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        gameWorld.generateMainCharacter(this.readInteger(fileReader));

        MainCharacter character = gameWorld.getMainCharacter();
        character.getExperience().setLevel(this.readInteger(fileReader));
        character.getExperience().setExperience(this.readInteger(fileReader));
        character.setHP(this.readInteger(fileReader));
        character.setMP(this.readInteger(fileReader));

        character.getSteps().setHPSteps(this.readInteger(fileReader));
        character.getSteps().setMPSteps(this.readInteger(fileReader));
        character.getSteps().setManaRiverSteps(this.readInteger(fileReader));

        for (int i = 0; i < 16; i++)
        {
            character.getStats().setStatPoints(i, this.readInteger(fileReader));
        }

        for (int i = 0; i < 16; i++)
        {
            character.getSkills().setSkillPoints(i, this.readInteger(fileReader));
        }

        for (int i = 0; i < 16; i++)
        {
            character.getSpells().setSpellPoints(i, this.readInteger(fileReader));
        }

        for (int i = 0; i < StatusEffects.NUM_STATUS_EFFECTS; i++)
        {
            character.getStatusEffects().setStatusEffect(i, this.readInteger(fileReader) == 1);
        }

        for (int i = 0; i < StatusEffects.NUM_STATUS_EFFECT_TURNS; i++)
        {
            character.getStatusEffects().setStatusEffectTurns(i, this.readInteger(fileReader));
        }

        for (int i = 0; i < StatusEffects.NUM_STATS; i++)
        {
            character.getStatusEffects().setStat(i, this.readInteger(fileReader));
        }
    }
}
