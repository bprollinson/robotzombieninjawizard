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

    public void initializeToDefaultState(int characterClass)
    {
        System.out.println("Generating dungeon level 1");
        this.character = characterGenerator.generateMainCharacter(characterClass);
        this.map = this.mapGenerator.generate(this.character, this.characterGenerator, 1);
    }

    public void generatePreviousMap()
    {
        System.out.println("Generating dungeon level " + (this.map.getLevel() - 1));
        this.map = this.mapGenerator.generate(this.character, this.characterGenerator, this.map.getLevel() - 1);
    }

    public void generateNextMap()
    {
        System.out.println("Generating dungeon level " + (this.map.getLevel() + 1));
        this.map = this.mapGenerator.generate(this.character, this.characterGenerator, this.map.getLevel() + 1);
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
