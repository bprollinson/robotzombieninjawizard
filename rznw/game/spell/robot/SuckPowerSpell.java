package rznw.game.spell.robot;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

public class SuckPowerSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Suck Power";
    }

    public String getDescription()
    {
        return "Steals MP from nearby enemies.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Suck Power");
        MainCharacter character = gameWorld.getMainCharacter();

        MapElement characterElement = character.getMapElement();
        Map map = gameWorld.getMap();
        int radius = 1 + (int)Math.floor(spellPoints / 2);
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            int maxMPStolen = 10 * spellPoints;
            int MPStolen = Math.min(enemy.getMP(), maxMPStolen);
            System.out.println("Stealing MP: " + MPStolen);

            character.healMP(MPStolen);
            enemy.setMP(enemy.getMP() - MPStolen);
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return 10;
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int radius = 1 + (int)Math.floor(spellPoints / 2);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "MP stolen: " + 10 * spellPoints,
            "Radius: " + radius
        };
    }
}
