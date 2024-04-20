package rznw.game.spell.wizard;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.FireElement;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;

public class RingOfFireSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Ring of Fire";
    }

    public String getDescription()
    {
        return "Creates a ring of fire three spaces away from you. The damage and duration increase as your spell level increases.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting ring of fire.");

        MainCharacter character = gameWorld.getMainCharacter();
        MapElement characterElement = character.getMapElement();
        Map map = gameWorld.getMap();
        int radius = 2;
        int duration = 2 + (int)Math.floor(spellPoints / 4);

        for (int row = characterElement.getRow() - radius; row <= characterElement.getRow() + radius; row++)
        {
            int column = characterElement.getColumn() - radius;
            if (row >= 0 && row < Map.NUM_ROWS && column >= 0 && column < Map.NUM_COLUMNS)
            {
                int localDuration = duration;
                MapElement existingElement = map.getBackgroundElement(row, column);
                if (existingElement instanceof FireElement)
                {
                    localDuration += ((FireElement)existingElement).getDuration();
                    existingElement = null;
                }

                FireElement fireElement = new FireElement(row, column, existingElement, localDuration);
                map.setBackgroundElement(row, column, fireElement);
            }

            column = characterElement.getColumn() + radius;
            if (row >= 0 && row < Map.NUM_ROWS && column >= 0 && column < Map.NUM_COLUMNS)
            {
                int localDuration = duration;
                MapElement existingElement = map.getBackgroundElement(row, column);
                if (existingElement instanceof FireElement)
                {
                    localDuration += ((FireElement)existingElement).getDuration();
                    existingElement = null;
                }

                FireElement fireElement = new FireElement(row, column, existingElement, localDuration);
                map.setBackgroundElement(row, column, fireElement);
            }
        }

        for (int column = characterElement.getColumn() - radius + 1; column <= characterElement.getColumn() + radius - 1; column++)
        {
            int row = characterElement.getRow() - radius;
            if (row >= 0 && row < Map.NUM_ROWS && column >= 0 && column < Map.NUM_COLUMNS)
            {
                int localDuration = duration;
                MapElement existingElement = map.getBackgroundElement(row, column);
                if (existingElement instanceof FireElement)
                {
                    localDuration += ((FireElement)existingElement).getDuration();
                    existingElement = null;
                }

                FireElement fireElement = new FireElement(row, column, existingElement, localDuration);
                map.setBackgroundElement(row, column, fireElement);
            }

            row = characterElement.getRow() + radius;
            if (row >= 0 && row < Map.NUM_ROWS && column >= 0 && column < Map.NUM_COLUMNS)
            {
                int localDuration = duration;
                MapElement existingElement = map.getBackgroundElement(row, column);
                if (existingElement instanceof FireElement)
                {
                    localDuration += ((FireElement)existingElement).getDuration();
                    existingElement = null;
                }

                FireElement fireElement = new FireElement(row, column, existingElement, localDuration);
                map.setBackgroundElement(row, column, fireElement);
            }
        }

        LogRendererFactory.instance().log("Created a ring of fire.");
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int duration = 2 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Radius: 2",
            "Turns: " + duration,
            "Damage per turn: " + 10 * spellPoints
        };
    }
}
