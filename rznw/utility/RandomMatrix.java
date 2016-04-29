package rznw.utility;

public class RandomMatrix
{
    private int[][] probabilityMatrix;
    private int[][] cumulativeProbabilityMatrix;

    public RandomMatrix(int[][] probabilityMatrix)
    {
        this.probabilityMatrix = probabilityMatrix;

        this.buildCumulativeProbabilityMatrix();
    }

    private void buildCumulativeProbabilityMatrix()
    {
        this.cumulativeProbabilityMatrix = new int[this.probabilityMatrix.length][this.probabilityMatrix[0].length];

        for (int row = 0; row < this.probabilityMatrix.length; row++)
        {
            int cumulativeProbability = 0;

            for (int column = 0; column < this.probabilityMatrix[0].length; column++)
            {
                cumulativeProbability += this.probabilityMatrix[row][column];
                this.cumulativeProbabilityMatrix[row][column] = cumulativeProbability;
            }
        }
    }

    public int getRandomSelection(int row)
    {
        int randomNumber = RandomNumberGenerator.randomInteger(1, 100);

        for (int column = 0; column < this.cumulativeProbabilityMatrix[0].length; column++)
        {
            if (randomNumber <= this.cumulativeProbabilityMatrix[row][column])
            {
                return column;
            }
        }

        return this.cumulativeProbabilityMatrix[0].length;
    }
}
