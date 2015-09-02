package rznw.ui;

import rznw.game.Character;

public class CharacterSummaryRenderer
{
    private MainGameFrame frame;

    public CharacterSummaryRenderer(MainGameFrame frame)
    {
        this.frame = frame;
    }

    public void render(Character character)
    {
        this.frame.renderDisplayString(21, 0, "HP: ");

        String hp = character.getHP() + "/" + character.getMaxHP();
        this.frame.renderDisplayString(21, "HP: ".length(), hp);
    }
}
