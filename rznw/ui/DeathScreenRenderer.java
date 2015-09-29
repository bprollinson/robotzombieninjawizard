package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

public class DeathScreenRenderer extends MenuScreenRenderer
{
    public DeathScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MainCharacter character)
    {
        this.clearScreen();
        this.renderCenteredString(1, "You are dead!");
        this.renderCenteredString(3, "You have died at character level: " + character.getLevel() + ".");
        this.renderCenteredString(5, "Challenge again!");
        this.renderCenteredString(30, "Press any key to continue...");
    }
}
