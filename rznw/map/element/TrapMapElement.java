package rznw.map.element;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.utility.RandomNumberGenerator;

public class TrapMapElement extends MapElement
{
    private static final int ELEMENT_NUMBER = 12;

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
        return sprung;
    }

    private void spring()
    {
        this.sprung = true;
    }

    private void find()
    {
        this.visible = true;
    }

    public void collideWithMainCharacter(GameWorld gameWorld, MainCharacter mainCharacter)
    {
        Map map = gameWorld.getMap();

        if (!this.isSprung())
        {
            this.spring();

            int disarmProbability = 5 * mainCharacter.getSkillPoints(11);

            if (RandomNumberGenerator.rollSucceeds(disarmProbability))
            {
                System.out.println("Disarmed trap");
            }
            else
            {
                System.out.println("It's a trap!");
                mainCharacter.damage(20, null, gameWorld, Character.DAMAGE_SOURCE_OTHER);
            }
        }
    }

    public void reveal(MainCharacter mainCharacter)
    {
        System.out.println("A trap is in range");
        int trapRevealProbability = 5 * mainCharacter.getStatPoints(7);
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
}
