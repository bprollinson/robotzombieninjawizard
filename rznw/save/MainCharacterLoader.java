package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

import java.io.BufferedReader;

public class MainCharacterLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        gameWorld.generateMainCharacter(this.readInteger(fileReader));

        MainCharacter character = gameWorld.getMainCharacter();
        character.setLevel(this.readInteger(fileReader));
        character.setExperience(this.readInteger(fileReader));
        character.setHP(this.readInteger(fileReader));
        character.setMP(this.readInteger(fileReader));

        for (int i = 0; i < 16; i++)
        {
            character.setStatPoints(i, this.readInteger(fileReader));
        }

        for (int i = 0; i < 16; i++)
        {
            character.setSkillPoints(i, this.readInteger(fileReader));
        }

        for (int i = 0; i < 16; i++)
        {
            character.setSpellPoints(i, this.readInteger(fileReader));
        }
    }
}
