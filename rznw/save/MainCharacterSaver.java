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

        for (int i = 0; i < 16; i++)
        {
            this.writeLine(fileWriter, mainCharacter.getStatPoints(i));
        }
    }
}
