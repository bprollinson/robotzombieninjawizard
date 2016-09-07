package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedWriter;

public class GameCompletedSaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        this.writeLine(fileWriter, gameWorld.gameCompleted() ? 1 : 0);
    }
}
