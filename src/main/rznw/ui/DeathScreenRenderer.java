package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.Map;

public class DeathScreenRenderer extends MenuScreenRenderer
{
    public DeathScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MainCharacter character, Map map)
    {
        this.clearScreen();
        this.renderCenteredString(1, "You are dead!");
        this.renderCenteredString(3, "You have died at character level: " + character.getExperience().getLevel() + ",");
        this.renderCenteredString(4, "on dungeon level: " + map.getLevel() + ".");
        this.renderCenteredString(6, "Challenge again!");
        this.renderCenteredString(30, "Press Enter to continue...");
    }
}
