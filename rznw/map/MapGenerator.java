package rznw.map;

import rznw.game.GameCharacter;
import rznw.map.element.CharacterMapElement;
import rznw.map.element.Wall;
import rznw.ui.MainGamePanel;

public class MapGenerator
{
    public Map generate(GameCharacter character)
    {
        Map map = new Map();

        for (int i = 0; i < Map.NUM_COLUMNS; i++)
        {
            map.setElement(0, i, new Wall(0, 1));
            map.setElement(Map.NUM_ROWS - 1, i, new Wall(Map.NUM_ROWS - 1, i));
        }

        for (int i = 1; i < Map.NUM_ROWS - 1; i++)
        {
            map.setElement(i, 0, new Wall(i, 0));
            map.setElement(i, Map.NUM_COLUMNS - 1, new Wall(i, Map.NUM_COLUMNS - 1));
        }

        CharacterMapElement characterMapElement = new CharacterMapElement(1, 1);
        map.setElement(1, 1, characterMapElement);
        character.setMapElement(characterMapElement);

        return map;
    }
}
