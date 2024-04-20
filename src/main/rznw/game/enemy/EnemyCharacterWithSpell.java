package rznw.game.enemy;

import rznw.game.enemy.spell.EnemySpell;

public abstract class EnemyCharacterWithSpell extends EnemyCharacter
{
    private int[] spells;

    public EnemyCharacterWithSpell(int level, int numSpells)
    {
        super(level);

        this.spells = new int[numSpells];

        for (int i = 0; i < this.spells.length; i++)
        {
            this.spells[i] = 0;
        }

        this.applySpellSequence();
    }

    private void applySpellSequence()
    {
        int desiredSequenceLength = 4 * this.level;

        int[] spellSequence = this.getSpellSequence();

        for (int i = 0; i < desiredSequenceLength; i++)
        {
            int statIndex = spellSequence[i % spellSequence.length];
            this.spells[statIndex]++;
        }
    }

    protected abstract int[] getSpellSequence();

    public abstract EnemySpell getSpell(int index);

    public int getSpellPoints(int index)
    {
        return this.spells[index];
    }
}
