package rznw.ui;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.skill.Skill;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;

public class DetectVitalityScreenRenderer
{
    private MainGameFrame frame;

    public DetectVitalityScreenRenderer(MainGameFrame frame)
    {
        this.frame = frame;
    }

    public void renderMap(Map map)
    {
        this.clearScreen();

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

        this.frame.renderDisplayString(30, 0, "Press up / down to change menus");
        this.frame.renderDisplayString(31, 0, "Press escape when done");
    }

    public void renderNumberedMap(MainCharacter character, Map map)
    {
        this.clearScreen();

        int digit = 0;

        for (int i = 0; i < Map.NUM_ROWS; i++)
        {
            for (int j = 0; j < Map.NUM_COLUMNS; j++)
            {
                MapElement element = map.getElement(i, j);
                MapElement backgroundElement = map.getBackgroundElement(i, j);

                char displayCharacter = ' ';

                boolean visible = map.isVisible(i, j);
                int skillPoints = character.getSkills().getSkillPoints(Skill.SKILL_DETECT_VITALITY);
                int radius = 1 + skillPoints;
                MapElement characterElement = character.getMapElement();
                double distance = Math.sqrt(Math.pow(characterElement.getRow() - i, 2) + Math.pow(characterElement.getColumn() - j, 2));

                if (element != null && element.isEnemy() && (visible || distance <= radius)) {
                    displayCharacter = Character.forDigit(digit, 10);
                    digit = (digit + 1) % 10;
                }
                else if (visible && element != null)
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

        this.frame.renderDisplayString(30, 0, "Press up / down to change menus");
        this.frame.renderDisplayString(31, 0, "Press escape when done");
    }

    public void renderEnemyHP(EnemyMapElement[] activeVisibleEnemies)
    {
         this.clearScreen();

         for (int i = 0; i < activeVisibleEnemies.length; i++)
         {
             EnemyCharacter enemy = (EnemyCharacter)activeVisibleEnemies[i].getCharacter();
             this.frame.renderDisplayString(i, 0, "" + i % 10 + ": " + enemy.getHP() + "/" + enemy.getMaxHP());
         }

        this.frame.renderDisplayString(30, 0, "Press up / down to change menus");
        this.frame.renderDisplayString(31, 0, "Press escape when done");
    }

    protected void clearScreen()
    {
        for (int i = 0; i < 32; i++)
        {
            for (int j = 0; j < 40; j++)
            {
                this.frame.renderDisplayCharacter(i, j, ' ');
            }
        }
    }
}
