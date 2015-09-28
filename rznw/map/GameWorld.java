package rznw.map;

import rznw.game.CharacterGenerator;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.generator.MapGenerator;

public class GameWorld
{
    private CharacterGenerator characterGenerator;
    private MapGenerator mapGenerator;
    private MainCharacter character;
    private Map map;

    public GameWorld(CharacterGenerator characterGenerator, MapGenerator mapGenerator)
    {
        this.characterGenerator = characterGenerator;
        this.mapGenerator = mapGenerator;
    }

    public void initializeToDefaultState()
    {
        this.character = characterGenerator.generateMainCharacter();
        this.generateMap();
    }

    public void generateMap()
    {
        this.map = this.mapGenerator.generate(this.character, this.characterGenerator);
    }

    public MainCharacter getMainCharacter()
    {
        return this.character;
    }

    public Map getMap()
    {
        return this.map;
    }
}
