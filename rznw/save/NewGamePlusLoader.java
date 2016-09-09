package rznw.save;

public class NewGamePlusLoader extends BaseGameLoader
{
    protected ComponentLoader[] getComponentLoaders()
    {
        return new ComponentLoader[] {
            new NewGamePlusGameCompletedLoader(),
            new MainCharacterLoader(),
            new ShopLoader(),
            new NewGamePlusEnemyLoader(),
            new NewGamePlusSummonLoader(),
            new NewGamePlusMapLoader(),
            new NewGamePlusMapVisibilityLoader(),
            new InventoryLoader(),
            new EquipmentLoader()
        };
    }
}
