package rznw.save;

import rznw.game.SummonedCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.SummonedMinionMapElement;

import java.io.BufferedWriter;

public class SummonSaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        Map map = gameWorld.getMap();

        this.writeLine(fileWriter, this.getNumSummons(map));

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);

                if (element instanceof SummonedMinionMapElement)
                {
                    SummonedMinionMapElement summonElement = (SummonedMinionMapElement)element;
                    SummonedCharacter summonCharacter = (SummonedCharacter)summonElement.getCharacter();

                    this.writeLine(fileWriter, summonCharacter.getSummonNumber());
                    this.writeLine(fileWriter, summonCharacter.getMaxHP());
                    this.writeLine(fileWriter, summonCharacter.getHP());
                }
            }
        }
    }

    private int getNumSummons(Map map)
    {
        int numSummons = 0;

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);

                if (element instanceof SummonedMinionMapElement)
                {
                    numSummons++;
                }
            }
        }

        return numSummons;
    }
}
