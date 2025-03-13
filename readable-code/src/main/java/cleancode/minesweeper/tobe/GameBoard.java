package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.cell.Cell2;
import cleancode.minesweeper.tobe.cell.EmptyCell;
import cleancode.minesweeper.tobe.cell.LandMineCell;
import cleancode.minesweeper.tobe.cell.NumberCell;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;

import java.util.Arrays;
import java.util.Random;

public class GameBoard {

    private final Cell2[][] board;
    private final int landMineCount;

    public GameBoard(GameLevel gameLevel) {
        int rowSize = gameLevel.getRowSize();
        int colSize = gameLevel.getColSize();
        board = new Cell2[rowSize][colSize];

        landMineCount = gameLevel.getLandMineCount();
    }

    public void flag(int selectedRowIndex, int selectedColIndex) {
        Cell2 cell = findCell(selectedRowIndex, selectedColIndex);
        cell.flag();
    }

    public void open(int selectedRowIndex, int selectedColIndex) {
        Cell2 cell = findCell(selectedRowIndex, selectedColIndex);
        cell.open();
    }

    public void openSurroundedCells(int row, int col) {
        if (row < 0 || row >= getRowSize() || col < 0 || col >= getColSize()) {
            return;
        }
        if (isOpenedCell(row, col)) {
            return;
        }
        if (isLandMineCell(row, col)) {
            return;
        }

        open(row, col);

        if (doesCellHaveLandMineCount(row, col)) {
            return;
        }

        openSurroundedCells(row - 1, col - 1);
        openSurroundedCells(row - 1, col);
        openSurroundedCells(row - 1, col + 1);
        openSurroundedCells(row, col - 1);
        openSurroundedCells(row, col + 1);
        openSurroundedCells(row + 1, col - 1);
        openSurroundedCells(row + 1, col);
        openSurroundedCells(row + 1, col + 1);
    }

    private boolean doesCellHaveLandMineCount(int row, int col) {
        return findCell(row, col).hasLandMineCount();
    }

    private boolean isOpenedCell(int row, int col) {
        return findCell(row, col).isOpened();
    }

    public boolean isLandMineCell(int selectedRowIndex, int selectedColIndex) {
        return findCell(selectedRowIndex, selectedColIndex).isLandMine();
    }

    public boolean isAllCellChecked() {
        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .allMatch(Cell2::isChecked);
    }

    public void initializeGame() {
        int rowSize = getRowSize();
        int colSize = getColSize();

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                board[row][col] = new EmptyCell();
            }
        }

        for (int i = 0; i < landMineCount; i++) {
            int landMineRow = new Random().nextInt(rowSize);
            int landMineCol = new Random().nextInt(colSize);
            LandMineCell landMineCell = new LandMineCell();
            landMineCell.turnOnLandMine();
            board[landMineRow][landMineCol] = landMineCell;
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (isLandMineCell(row, col)) {
                    continue;
                }
                int count = countNearbyLandMines(row, col);
                if (count == 0) {
                    continue;
                }
                NumberCell numberCell = new NumberCell();
                numberCell.updateNearbyLandMineCount(count);
                board[row][col] = numberCell;
            }
        }
    }

    public String getSign(int rowIndex, int colIndex) {
        Cell2 cell = findCell(rowIndex, colIndex);
        return cell.getSign();
    }

    private Cell2 findCell(int rowIndex, int colIndex) {
        return board[rowIndex][colIndex];
    }

    public int getRowSize() {
        return board.length;
    }

    public int getColSize() {
        return board[0].length;
    }

    private int countNearbyLandMines(int row, int col) {
        int rowSize = getRowSize();
        int colSize = getColSize();

        int count = 0;
        if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (row - 1 >= 0 && isLandMineCell(row - 1, col)) {
            count++;
        }
        if (row - 1 >= 0 && col + 1 < colSize && isLandMineCell(row - 1, col + 1)) {
            count++;
        }
        if (col - 1 >= 0 && isLandMineCell(row, col - 1)) {
            count++;
        }
        if (col + 1 < colSize && isLandMineCell(row, col + 1)) {
            count++;
        }
        if (row + 1 < rowSize && col - 1 >= 0 && isLandMineCell(row + 1, col - 1)) {
            count++;
        }
        if (row + 1 < rowSize && isLandMineCell(row + 1, col)) {
            count++;
        }
        if (row + 1 < rowSize && col + 1 < colSize && isLandMineCell(row + 1, col + 1)) {
            count++;
        }
        return count;
    }

}
