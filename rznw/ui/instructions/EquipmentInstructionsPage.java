package rznw.ui.instructions;

public class EquipmentInstructionsPage extends InstructionsPage
{
    public String getPageTitle()
    {
        return "Equipment";
    }

    public String[] getPageContent()
    {
        return new String[] {
            "In your adventures, you will encounter equipment from enemy drops and in the shopkeeper's store.",
            "",
            "Equipment comes in three varieties: weapons, shields and armor.",
            "",
            "Weapons increase your offensive capabilities, while shields and armor increase your defensive abilities.",
            "",
            "Equipment may also provide other bonuses. See specific equipment descriptions on the equipment menu for details.",
            "",
            "You may equip one of each equipment type at any time via the equipment menu."
        };
    }
}
