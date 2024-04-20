package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.game.statuseffects.StatusEffectStats;
import rznw.game.statuseffects.TurnBasedStatusEffects;
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

        for (int i = 0; i < SimpleStatusEffects.NUM_STATUS_EFFECTS; i++)
        {
            character.getStatusEffects().setStatusEffect(i, this.readInteger(fileReader) == 1);
        }

        for (int i = 0; i < TurnBasedStatusEffects.NUM_STATUS_EFFECTS; i++)
        {
            character.getStatusEffects().setStatusEffectTurns(i, this.readInteger(fileReader));
        }

        for (int i = 0; i < StatusEffectStats.NUM_STATS; i++)
        {
            character.getStatusEffects().setStat(i, this.readInteger(fileReader));
        }
    }
}
