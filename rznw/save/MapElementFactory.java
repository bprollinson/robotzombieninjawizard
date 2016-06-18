package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.element.Blotch;
import rznw.map.element.DisappearingEnemyMapElement;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.FireElement;
import rznw.map.element.MainCharacterMapElement;
import rznw.map.element.MapElement;
import rznw.map.element.Path;
import rznw.map.element.Projectile;
import rznw.map.element.RockWall;
import rznw.map.element.Stairs;
import rznw.map.element.SummonedGolemMapElement;
import rznw.map.element.SummonedZombieMapElement;
import rznw.map.element.TrapMapElement;
import rznw.map.element.Void;
import rznw.map.element.Wall;
import rznw.map.element.Waypoint;

public class MapElementFactory
{
    public static MapElement factory(GameWorld gameWorld, int elementIndex, int row, int column)
    {
        switch (elementIndex)
        {
            case Blotch.ELEMENT_NUMBER:
                break;
            case DisappearingEnemyMapElement.ELEMENT_NUMBER:
                break;
            case EnemyMapElement.ELEMENT_NUMBER:
                break;
            case FireElement.ELEMENT_NUMBER:
                break;
            case MainCharacterMapElement.ELEMENT_NUMBER:
                MainCharacter mainCharacter = gameWorld.getMainCharacter();
                MainCharacterMapElement element = new MainCharacterMapElement(row, column, '?', mainCharacter);
                mainCharacter.setMapElement(element);

                return element;
            case Path.ELEMENT_NUMBER:
                break;
            case Projectile.ELEMENT_NUMBER:
                break;
            case RockWall.ELEMENT_NUMBER:
                break;
            case Stairs.ELEMENT_NUMBER:
                break;
            case SummonedGolemMapElement.ELEMENT_NUMBER:
                break;
            case SummonedZombieMapElement.ELEMENT_NUMBER:
                break;
            case TrapMapElement.ELEMENT_NUMBER:
                break;
            case Void.ELEMENT_NUMBER:
                break;
            case Wall.ELEMENT_NUMBER:
                return new Wall(row, column);
            case Waypoint.ELEMENT_NUMBER:
                break;
        }

        return null;
    }
}
