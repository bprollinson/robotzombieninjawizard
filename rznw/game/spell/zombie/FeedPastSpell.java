package rznw.game.spell.zombie;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;

public class FeedPastSpell extends Spell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Feed Past");
        MainCharacter character = gameWorld.getMainCharacter();
        Map map = gameWorld.getMap();

        MapElement characterElement = character.getMapElement();

        int deltaRow = 0;
        int deltaColumn = 0;

        switch(direction)
        {
            case Spell.DIRECTION_UP:
                deltaRow = -1;
                break;
            case Spell.DIRECTION_DOWN:
                deltaRow = 1;
                break;
            case Spell.DIRECTION_LEFT:
                deltaColumn = -1;
                break;
            case Spell.DIRECTION_RIGHT:
                deltaColumn = 1;
                break;
        }

        int row = characterElement.getRow() + deltaRow;
        int column = characterElement.getColumn() + deltaColumn;

        MapElement element = map.getElement(row, column);
        if (element instanceof EnemyMapElement)
        {
            int damage = 50 + 10 * spellPoints;

            Character enemy = ((EnemyMapElement)element).getCharacter();
            int experiencePercentage = 5 * spellPoints;
            int experience = (int)Math.floor(experiencePercentage * ((EnemyCharacter)enemy).getExperienceReward() / 100.0);

            System.out.println("Before: " + enemy.getHP());
            enemy.damage(damage);
            System.out.println("After: " + enemy.getHP());

            System.out.println("Bonus experience: " + experience);
            gameWorld.getMainCharacter().grantExperience(experience);
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public boolean requiresDirectionInput()
    {
        return true;
    }
}
