package rznw.ui;

import rznw.map.Map;
import rznw.map.element.MapElement;

public class MapRenderer
{
    private MainGameFrame frame;

    public MapRenderer(MainGameFrame frame)
    {
        this.frame = frame;
    }

    public void render(Map map)
    {
        for (int i = 0; i < Map.NUM_ROWS; i++)
        {
            for (int j = 0; j < Map.NUM_COLUMNS; j++)
            {
                MapElement element = map.getElement(i, j);
                MapElement backgroundElement = map.getBackgroundElement(i, j);

                char displayCharacter = ' ';

                boolean visible = map.isVisible(i, j);

                if (visible && element != null)
                {
                    displayCharacter = element.getDisplayCharacter();
                }
                else if (visible && backgroundElement != null)
                {
                    displayCharacter = backgroundElement.getDisplayCharacter();
                }

                this.frame.renderDisplayCharacter(i, j, displayCharacter);
            }
        }
    }
}
