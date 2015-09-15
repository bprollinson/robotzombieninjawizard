package rznw.map;

import rznw.game.CharacterGenerator;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.generator.MapGenerator;

public class GameWorld
{
    private MainCharacter character;
    private CharacterGenerator characterGenerator;
    private MapGenerator mapGenerator;
    private Map map;

    public GameWorld(MainCharacter character, CharacterGenerator characterGenerator, MapGenerator mapGenerator)
    {
        this.character = character;
        this.characterGenerator = characterGenerator;
        this.mapGenerator = mapGenerator;
    }

    public void generateMap()
    {
        this.map = this.mapGenerator.generate(this.character, this.characterGenerator);
    }

    public Map getMap()
    {
        return this.map;
    }
}
