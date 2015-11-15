package rznw.game.spell.robot;

import rznw.game.Character;
import rznw.game.maincharacter.KillBonusGranter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public class ParalyzingBlastSpell extends Spell
{
    private KillBonusGranter killBonusGranter;

    public ParalyzingBlastSpell()
    {
        this.killBonusGranter = new KillBonusGranter();
    }

    public boolean canCast(MainCharacter character)
    {
        return character.getSpellPoints(7) > 0 && character.getMP() >= this.getMPCost(character);
    }

    public void cast(GameWorld gameWorld)
    {
    }

    public void cast(GameWorld gameWorld, int direction)
    {
        System.out.println("Casting Paralyzing Blast");

        MainCharacter character = gameWorld.getMainCharacter();
        character.setMP(character.getMP() - this.getMPCost(character));

        int damage = 20 + 10 * character.getSpellPoints(7);

        int deltaRow = 0;
        int deltaColumn = 0;

        switch(direction)
        {
            case Spell.DIRECTION_UP:
                deltaRow = -1;
                break;
            case Spell.DIRECTION_DOWN:
                deltaRow = 1;
                break;
            case Spell.DIRECTION_LEFT:
                deltaColumn = -1;
                break;
            case Spell.DIRECTION_RIGHT:
                deltaColumn = 1;
                break;
        }

        boolean objectFound = false;
        int row = character.getMapElement().getRow();
        int column = character.getMapElement().getColumn();

        while (!objectFound)
        {
            row += deltaRow;
            column += deltaColumn;

            Map map = gameWorld.getMap();
            MapElement element = map.getElement(row, column);

            if (element != null)
            {
                objectFound = true;
            }

            if (element instanceof EnemyMapElement)
            {
                System.out.println("Direct hit " + element);

                Character enemy = ((EnemyMapElement)element).getCharacter();
                System.out.println("Before: " + enemy.getHP());
                enemy.damage(damage);
                System.out.println("After: " + enemy.getHP());

                if (enemy.isDead())
                {
                    this.killBonusGranter.grantKillBonuses(character, enemy);
                    map.setElement(element.getRow(), element.getColumn(), null);
                }
                else
                {
                    int probabilityToFreeze = 5 * character.getSpellPoints(7);
                    int random = RandomNumberGenerator.randomInteger(1, 100);

                    if (random <= probabilityToFreeze)
                    {
                        System.out.println("Enemy paralyzed");
                        enemy.getStatusEffects().freeze();
                    }
                }
            }
        }
    }

    private int getMPCost(MainCharacter character)
    {
        int spellLevel = character.getSpellPoints(7);
        return Math.max(200 - 10 * spellLevel, 1);
    }

    public boolean requiresDirectionInput()
    {
        return true;
    }
}
