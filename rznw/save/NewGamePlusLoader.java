package rznw.save;

public class NewGamePlusLoader extends BaseGameLoader
{
    protected ComponentLoader[] getComponentLoaders()
    {
        return new ComponentLoader[] {
            new GameCompletedLoader(),
            new MainCharacterLoader(),
            new NewGamePlusShopLoader(),
            new NewGamePlusEnemyLoader(),
            new NewGamePlusSummonLoader(),
            new NewGamePlusMapLoader(),
            new NewGamePlusMapVisibilityLoader(),
            new InventoryLoader(),
            new EquipmentLoader()
        };
    }
}
