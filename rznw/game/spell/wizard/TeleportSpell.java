package rznw.game.spell.wizard;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapElementSetter;
import rznw.map.TeleportSquareCalculator;
import rznw.map.element.MapElement;

public class TeleportSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Teleport";
    }

    public String getDescription()
    {
        return "Allows you to escape your surroundings, moving you to a safer location. As your spell level increases, the probability of arriving in a safe location increases.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Teleport");

        MainCharacter character = gameWorld.getMainCharacter();

        MapElement newPositionElement = this.getNewPositionElement(gameWorld, spellPoints);

        Map map = gameWorld.getMap();
        MapElement characterMapElement = character.getMapElement();

        map.setElement(characterMapElement.getRow(), characterMapElement.getColumn(), null);
        MapElementSetter.setElement(map, characterMapElement, newPositionElement.getRow(), newPositionElement.getColumn());
        map.setElementVisited(character, newPositionElement.getRow(), newPositionElement.getColumn());
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    private MapElement getNewPositionElement(GameWorld gameWorld, int spellPoints)
    {
        double safetyPercentage = Math.floor(50 + 50 * Math.min(spellPoints / 20.0, 1));
        System.out.println("safety percentage: " + safetyPercentage);

        return new TeleportSquareCalculator(gameWorld).getMapElementWithSafetyPercentage(safetyPercentage);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        double positionPercentage = Math.floor(50 + 50 * Math.min(spellPoints / 20.0, 1));

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Safety percentage: " + positionPercentage + "%"
        };
    }
}
