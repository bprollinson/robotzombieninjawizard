package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.game.statuseffects.StatusEffectStats;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;

import java.io.BufferedReader;

class NewGamePlusMainCharacterLoader extends MainCharacterLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        gameWorld.generateMainCharacter(this.readInteger(fileReader));

        MainCharacter character = gameWorld.getMainCharacter();
        character.getExperience().setLevel(this.readInteger(fileReader));
        character.getExperience().setExperience(this.readInteger(fileReader));
        int HP = this.readInteger(fileReader);
        int MP = this.readInteger(fileReader);

        int HPSteps = this.readInteger(fileReader);
        character.getSteps().setHPSteps(0);
        int MPSteps = this.readInteger(fileReader);
        character.getSteps().setMPSteps(0);
        int manaRiverSteps = this.readInteger(fileReader);
        character.getSteps().setManaRiverSteps(0);

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
            boolean simpleStatusEffect = this.readInteger(fileReader) == 1;
            character.getStatusEffects().setStatusEffect(i, false);
        }

        for (int i = 0; i < TurnBasedStatusEffects.NUM_STATUS_EFFECTS; i++)
        {
            int statusEffectTurns = this.readInteger(fileReader);
            character.getStatusEffects().setStatusEffectTurns(i, 0);
        }

        for (int i = 0; i < StatusEffectStats.NUM_STATS; i++)
        {
            int statusEffectStatus = this.readInteger(fileReader);
            character.getStatusEffects().setStat(i, 0);
        }

        character.setHP(character.getMaxHP());
        character.setMP(character.getMaxMP());
    }
}
