package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;

import java.io.BufferedReader;

public class MapVisibilityLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        Map map = gameWorld.getMap();
        MainCharacter character = gameWorld.getMainCharacter();

        int numVisibleElements = this.readInteger(fileReader);

        for (int i = 0; i < numVisibleElements; i++)
        {
            int row = this.readInteger(fileReader);
            int column = this.readInteger(fileReader);

            map.setVisible(character, row, column);
        }
    }
}
