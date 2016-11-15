package rznw.map.element;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.skill.Skill;
import rznw.game.stat.Stat;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class TrapMapElement extends MapElement
{
    public static final int ELEMENT_NUMBER = 12;

    private boolean visible = false;
    private boolean sprung = false;

    public TrapMapElement(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        if (this.sprung)
        {
            return '-';
        }

        if (this.visible)
        {
            return '_';
        }

        return ' ';
    }

    private boolean isSprung()
    {
        return this.sprung;
    }

    public void spring()
    {
        this.sprung = true;
    }

    private boolean isFound()
    {
        return this.visible;
    }

    public void find()
    {
        this.visible = true;
    }

    public void collideWithMainCharacter(GameWorld gameWorld, MainCharacter mainCharacter)
    {
        Map map = gameWorld.getMap();

        if (!this.isSprung())
        {
            this.spring();

            int disarmProbability = 5 * mainCharacter.getSkills().getSkillPoints(Skill.SKILL_DISARM_TRAPS);

            if (RandomNumberGenerator.rollSucceeds(disarmProbability))
            {
                System.out.println("Disarmed trap");
            }
            else
            {
                int damage = mainCharacter.damage(20, null, gameWorld, Character.DAMAGE_SOURCE_OTHER);
                LogRendererFactory.instance().log("Hit by a trap for " + damage + " damage.");
            }
        }
    }

    public void reveal(MainCharacter mainCharacter)
    {
        System.out.println("A trap is in range");
        int trapRevealProbability = 5 * mainCharacter.getStats().getStatPoints(Stat.STAT_FIND_TRAPS);
        System.out.println("Trap reveal probability: " + trapRevealProbability);

        if (RandomNumberGenerator.rollSucceeds(trapRevealProbability))
        {
            System.out.println("Detected the trap!");
            this.find();
        }
    }

    public int getElementNumber()
    {
        return TrapMapElement.ELEMENT_NUMBER;
    }

    public String getMetadata()
    {
        String isSprungDisplay = this.isSprung() ? "1" : "0";
        String isFoundDisplay = this.isFound() ? "1" : "0";

        return isSprungDisplay + "," + isFoundDisplay;
    }
}
