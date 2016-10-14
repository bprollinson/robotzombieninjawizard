package rznw.game.spell.ninja;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapElementSetter;
import rznw.map.TeleportSquareCalculator;
import rznw.map.element.MapElement;

public class SmokeBombSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Smoke Bomb";
    }

    public String getDescription()
    {
        return "The next time you are damaged by an enemy, you have a chance to escape to a safe location.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Smoke Bomb");

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_SMOKE_BOMB, true);
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public static void escape(GameWorld gameWorld)
    {
        System.out.println("Escaping with smoke bomb");

        MainCharacter character = gameWorld.getMainCharacter();
        int spellPoints = character.getSpells().getSpellPoints(NinjaSpellFactory.SPELL_SMOKE_BOMB);

        MapElement newPositionElement = SmokeBombSpell.getNewPositionElement(gameWorld, spellPoints);

        Map map = gameWorld.getMap();
        int oldRow = character.getMapElement().getRow();
        int oldColumn = character.getMapElement().getColumn();

        map.setElement(oldRow, oldColumn, null);
        MapElementSetter.setElement(map, character.getMapElement(), newPositionElement.getRow(), newPositionElement.getColumn());
        map.setElementVisited(character, newPositionElement.getRow(), newPositionElement.getColumn());
    }

    private static MapElement getNewPositionElement(GameWorld gameWorld, int spellPoints)
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
            "Chance to escape: " + 5 * spellPoints + "%",
            "Safety percentage: " + positionPercentage + "%"
        };
    }
}
