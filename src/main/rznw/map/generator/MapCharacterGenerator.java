package rznw.map.generator;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.Map;
import rznw.map.element.MainCharacterMapElement;
import rznw.map.generator.area.MapArea;
import rznw.utility.RandomNumberGenerator;

import java.util.List;

public class MapCharacterGenerator
{
    public void placeCharacter(Map map, MainCharacter character, List<MapArea> rooms)
    {
        int roomIndex = RandomNumberGenerator.randomInteger(0, rooms.size() - 1);
        MapArea room = rooms.get(roomIndex);
        int posX = RandomNumberGenerator.randomInteger(room.getStartColumn() + 1, room.getEndColumn() - 1);
        int posY = RandomNumberGenerator.randomInteger(room.getStartRow() + 1, room.getEndRow() - 1);

        character.generateMapElement(posY, posX);
        MainCharacterMapElement characterMapElement = (MainCharacterMapElement)character.getMapElement();
        map.setElement(posY, posX, characterMapElement);
    }
}
