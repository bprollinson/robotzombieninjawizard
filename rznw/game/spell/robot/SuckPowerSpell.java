package rznw.game.spell.robot;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

public class SuckPowerSpell extends Spell
{
    public void cast(GameWorld gameWorld)
    {
        System.out.println("Casting Suck Power");
        MainCharacter character = gameWorld.getMainCharacter();

        MapElement characterElement = character.getMapElement();
        Map map = gameWorld.getMap();
        int radius = 1 + (int)Math.floor(character.getSpellPoints(3) / 2);
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            int maxMPStolen = 10 * character.getSpellPoints(3);
            int MPStolen = Math.min(enemy.getMP(), maxMPStolen);
            System.out.println("Stealing MP: " + MPStolen);

            character.healMP(MPStolen);
            enemy.setMP(enemy.getMP() - MPStolen);
        }
    }

    public int getMPCost(MainCharacter character)
    {
        return 10;
    }
}
