package life;

public class LifeAlgorithm {

    public static int countNeighbours(boolean[][] gameBoard, int lifeRow, int lifeColumn) {
        int neighbours = 0, neighbourRow, neighbourColumn;
        int N = gameBoard.length;
        int M = gameBoard[0].length;

        for (int p = -1; p <= 1; p++) {
            for (int m = -1; m <= 1; m++) {
                neighbourRow = lifeRow + p;
                neighbourColumn = lifeColumn + m;
                neighbourRow = (neighbourRow + N) % N;
                neighbourColumn = (neighbourColumn + M) % M;
                if (gameBoard[neighbourRow][neighbourColumn] && (p != 0 || m != 0)) {
                    neighbours++;
                }
            }
        }
        return neighbours;
    }

    public static Generation generateNextGeneration(Generation previousGeneration) {
        int aliveCells = 0;
        int size = previousGeneration.getGameBoard().length;
        boolean[][] nextGeneration = new boolean[size][size];
        int neighbours;
        for (int rows = 0; rows < size; rows++) {
            for (int columns = 0; columns < size; columns++) {
                neighbours = countNeighbours(previousGeneration.getGameBoard(), rows, columns);
                if (neighbours == 3 || (previousGeneration.getGameBoard()[rows][columns] && neighbours == 2)) {
                    aliveCells++;
                    nextGeneration[rows][columns] = true;
                } else {
                    nextGeneration[rows][columns] = false;
                }
            }
        }
        return new Generation(aliveCells, nextGeneration);
    }
}
