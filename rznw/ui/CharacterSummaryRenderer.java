package rznw.ui;

import rznw.game.Character;
import rznw.map.GameWorld;
import rznw.map.Map;

public class CharacterSummaryRenderer
{
    private MainGameFrame frame;

    public CharacterSummaryRenderer(MainGameFrame frame)
    {
        this.frame = frame;
    }

    public void render(GameWorld gameWorld)
    {
        this.clearCharacterSummaryArea();

        this.frame.renderDisplayString(Map.NUM_ROWS + 1, 0, "HP: ");

        Character character = gameWorld.getMainCharacter();
        String hp = character.getHP() + "/" + character.getMaxHP();
        this.frame.renderDisplayString(Map.NUM_ROWS + 1, "HP: ".length(), hp);

        this.frame.renderDisplayString(Map.NUM_ROWS + 1, 20, "MP: ");
        String mp = character.getMP() + "/" + character.getMaxMP();
        this.frame.renderDisplayString(Map.NUM_ROWS + 1, 20 + "MP: ".length(), mp);
    }

    private void clearCharacterSummaryArea()
    {
        for (int i = 0; i < MainGamePanel.NUM_SUMMARY_ROWS; i++)
        {
            for (int j = 0; j < Map.NUM_COLUMNS; j++)
            {
                this.frame.renderDisplayCharacter(Map.NUM_ROWS + i, j, ' ');
            }
        }
    }
}
