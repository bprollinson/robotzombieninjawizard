package rznw.ui.instructions;

public class InventoryInstructionsPage extends InstructionsPage
{
    public String getPageTitle()
    {
        return "Inventory";
    }

    public String[] getPageContent()
    {
        return new String[] {
            "In your adventures, you will encounter items from enemy drops and in the shopkeeper's store.",
            "",
            "The most common uses of items are for healing, recovering MP, or relieving a status effect.",
            "",
            "Items can be viewed or used from the inventory menu."
        };
    }
}
