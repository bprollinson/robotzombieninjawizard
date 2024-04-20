package rznw.map.element;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.wizard.WizardSpellFactory;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.ui.LogRendererFactory;
import rznw.utility.StringUtils;

public class FireElement extends MapElement
{
    public static final int ELEMENT_NUMBER = 4;

    private MapElement previousMapElement;
    private int duration;

    public FireElement(int row, int column, MapElement previousMapElement, int duration)
    {
        super(row, column);

        this.previousMapElement = previousMapElement;
        this.duration = duration;
    }

    public void decreaseDuration()
    {
        this.duration--;
    }

    public boolean isExpired()
    {
        return this.duration <= 0;
    }

    public int getDuration()
    {
        return this.duration;
    }

    public MapElement getPreviousMapElement()
    {
        return this.previousMapElement;
    }

    public char getDisplayCharacter()
    {
        return 'F';
    }

    public void collideWithEnemy(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        int damage = 10 * character.getSpells().getSpellPoints(WizardSpellFactory.SPELL_RING_OF_FIRE);
        int damageDealt = enemyCharacter.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);

        StringUtils utils = new StringUtils();
        LogRendererFactory.instance().log(utils.UCFirst(enemyCharacter.getLogName()) + " burned for " + damageDealt + " damage.");
    }

    public void processTurn(Map map)
    {
        this.decreaseDuration();

        if (this.isExpired())
        {
            MapElement previousMapElement = this.getPreviousMapElement();
            map.setBackgroundElement(this.getRow(), this.getColumn(), previousMapElement);
        }
    }

    public int getElementNumber()
    {
        return FireElement.ELEMENT_NUMBER;
    }

    public String getMetadata()
    {
        String previousData = "";
        MapElement previousElement = this.getPreviousMapElement();
        if (previousElement != null)
        {
            previousData += previousElement.getElementNumber() + "/" + previousElement.getMetadata();
        }

        return "" + this.getDuration() + "/" + previousData;
    }
}
