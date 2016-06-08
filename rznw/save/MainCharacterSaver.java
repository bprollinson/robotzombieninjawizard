package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

import java.io.BufferedWriter;

public class MainCharacterSaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        MainCharacter mainCharacter = gameWorld.getMainCharacter();

        this.writeLine(fileWriter, mainCharacter.getLevel());
        this.writeLine(fileWriter, mainCharacter.getExperience());

        this.writeLine(fileWriter, mainCharacter.getHP());
        this.writeLine(fileWriter, mainCharacter.getMP());

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
            this.writeLine(fileWriter, mainCharacter.getSpellPoints(i));
        }
    }
}
