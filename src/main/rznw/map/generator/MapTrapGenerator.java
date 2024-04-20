package rznw.map.generator;

import rznw.map.Map;
import rznw.map.element.TrapMapElement;
import rznw.map.generator.area.MapArea;
import rznw.utility.RandomNumberGenerator;

import java.util.List;

public class MapTrapGenerator
{
    private static int BASE_TRAP_PROBABILITY = 10;
    private static int TRAP_PROBABILITY_PER_LEVEL = 5;

    public void generateTraps(Map map, List<MapArea> rooms)
    {
        for (int i = 0; i < rooms.size(); i++)
        {
            MapArea room = rooms.get(i);
            for (int row = room.getStartRow() + 1; row < room.getEndRow(); row++)
            {
                for (int column = room.getStartColumn() + 1; column < room.getEndColumn(); column++)
                {
                    if (map.getBackgroundElement(row, column) == null && RandomNumberGenerator.rollSucceeds(this.getTrapProbability(map.getLevel())))
                    {
                        TrapMapElement trap = new TrapMapElement(row, column);
                        map.setBackgroundElement(row, column, trap);
                    }
                }
            }
        }
    }

    private int getTrapProbability(int level)
    {
        return MapTrapGenerator.BASE_TRAP_PROBABILITY + (level - 1) * MapTrapGenerator.TRAP_PROBABILITY_PER_LEVEL;
    }
}
