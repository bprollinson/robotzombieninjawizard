package rznw.game.spell.zombie;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.ui.LogRendererFactory;

public class FeedFleshSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Feed Flesh";
    }

    public String getDescription()
    {
        return "Deals damage to an enemy and heals some of your HP. Has the same range as melee combat.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        LogRendererFactory.instance().log("Casting feed flesh.");
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
            int healHP = (int)Math.floor(damage / 2);

            Character enemy = ((EnemyMapElement)element).getCharacter();
            damage = enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            LogRendererFactory.instance().log("Dealt " + damage + " damage to " + enemy.getLogName() + ".");

            int HPHealed = gameWorld.getMainCharacter().heal(healHP);
            LogRendererFactory.instance().log("You healed " + HPHealed + " HP.");
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int damage = 50 + 10 * spellPoints;
        int healHP = (int)Math.floor(damage / 2);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + damage,
            "Healing: " + healHP + " HP"
        };
    }
}
