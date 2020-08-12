package life;

import java.util.Random;

public class Generation {

    private final boolean[][] gameBoard;
    private int aliveCells;

    public Generation(int aliveCells, boolean[][] gameBoard) {
        this.aliveCells = aliveCells;
        this.gameBoard = gameBoard.clone();
    }

    public Generation(int size) {
        Random randomNumber = new Random(System.currentTimeMillis());
        this.gameBoard = new boolean[size][size];
        for (int rows = 0; rows < size; rows++) {
            for (int columns = 0; columns < size; columns++) {
                if (randomNumber.nextBoolean()) {
                    aliveCells++;
                    this.gameBoard[rows][columns] = true;
                }
            }
        }
    }

    public int getAliveCells() {
        return aliveCells;
    }

    public boolean[][] getGameBoard() {
        return gameBoard.clone();
    }
}
