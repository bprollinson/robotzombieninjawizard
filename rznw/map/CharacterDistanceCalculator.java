package rznw.map;

import rznw.game.Character;
import rznw.map.element.MapElement;

public class CharacterDistanceCalculator
{
    public double calculateDistance(Character character1, Character character2)
    {
        MapElement element1 = character1.getMapElement();
        MapElement element2 = character2.getMapElement();

        return Math.max(Math.abs(element2.getRow() - element1.getRow()), Math.abs(element2.getColumn() - element1.getColumn()));
    }
}
