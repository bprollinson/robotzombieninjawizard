package rznw.game.spell.zombie;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.ui.LogRendererFactory;

public class FeedPastSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Feed Past";
    }

    public String getDescription()
    {
        return "Deals damage to an enemy and grants you some experience. Has the same range as melee combat.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        LogRendererFactory.instance().log("Casting feed past.");
        MainCharacter character = gameWorld.getMainCharacter();
        Map map = gameWorld.getMap();

        MapElement characterElement = character.getMapElement();

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(direction);

        int row = characterElement.getRow() + positionChange.getDeltaRow();
        int column = characterElement.getColumn() + positionChange.getDeltaColumn();

        MapElement element = map.getElement(row, column);
        if (element != null && element.isEnemy())
        {
            int damage = 50 + 10 * spellPoints;

            Character enemy = ((EnemyMapElement)element).getCharacter();
            int experiencePercentage = 5 * spellPoints;
            int experience = (int)Math.floor(experiencePercentage * ((EnemyCharacter)enemy).getExperienceReward() / 100.0);

            damage = enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            LogRendererFactory.instance().log("Dealt " + damage + " damage to " + enemy.getLogName() + ".");

            gameWorld.getMainCharacter().getExperience().grantExperience(experience);
            LogRendererFactory.instance().log("Gained " + experience + " bonus experience.");
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int damage = 50 + 10 * spellPoints;

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + damage,
            "Experience: " + 5 * spellPoints + "% of enemy experience"
        };
    }
}
