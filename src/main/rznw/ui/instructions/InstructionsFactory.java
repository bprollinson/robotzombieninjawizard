package rznw.ui.instructions;

public class InstructionsFactory
{
    public static int NUM_PAGES = 11;

    public static InstructionsPage getPage(int index)
    {
        switch (index)
        {
            case 0:
                return new IntroInstructionsPage();
            case 1:
                return new MovementInstructionsPage();
            case 2:
                return new CombatInstructionsPage();
            case 3:
                return new MagicInstructionsPage();
            case 4:
                return new EquipmentInstructionsPage();
            case 5:
                return new StatsInstructionsPage();
            case 6:
                return new SkillsInstructionsPage();
            case 7:
                return new InventoryInstructionsPage();
            case 8:
                return new StatusEffectsPage();
            case 9:
                return new MapLegend1Page();
            case 10:
                return new MapLegend2Page();
        }

        return null;
    }
}
