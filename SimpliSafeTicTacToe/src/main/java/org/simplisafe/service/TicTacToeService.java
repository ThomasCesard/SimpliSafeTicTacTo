package org.simplisafe.service;

import org.simplisafe.domain.TicTacToeBoard;
import org.simplisafe.domain.TicTacToeBoard.Tile;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class TicTacToeService {

    /**
     * Compute if there is a winner in a game of tic-tac-toe.
     * There is a winner if there are four of the same tiles in
     * a row vertically, horizontally, or diagonally. There is
     * also a winner if the same tile is in all four corners,
     * or in a 2x2 box on the board.
     *
     * @param board a valid 4x4 tic-tac-toe board.
     * @return true if there is a winner, false otherwise.
     */
    public boolean isWinner(TicTacToeBoard board) {
        return checkCorners(board.getTiles()) || checkDiagonals(board.getTiles()) || checkVerticals(board.getTiles())
                || checkHorizontals(board.getTiles()) || checkBoxes(board.getTiles());
    }

    /**
     * Compute if a valid move can be made in a game of tic-tac-toe.
     * A valid move can be made if there is an empty tile on the board,
     * regardless if the game has been won or not.
     *
     * @param board a valid 4x4 tic-tac-toe board.
     * @return true if a move can be made, false otherwise.
     */
    public boolean canMove(TicTacToeBoard board) {
        return board.getTiles().stream()
                .flatMap(Collection::stream)
                .anyMatch(Tile.E::equals);
    }

    /**
     * Compute if a game of tic-tac-toe is over. A game is over
     * if there are no more valid moves, or if there is a winner.
     *
     * @param board a valid 4x4 tic-tac-toe board.
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver(TicTacToeBoard board) {
        return !canMove(board) || isWinner(board);
    }

    private boolean checkCorners (List<List<Tile>> tiles) {
        Tile firstTile = tiles.get(0).get(0);
        return !Tile.E.equals(firstTile)
                && firstTile.equals(tiles.get(0).get(3))
                && firstTile.equals(tiles.get(3).get(0))
                && firstTile.equals(tiles.get(3).get(3));
    }

    private boolean checkDiagonals(List<List<Tile>> tiles) {
        Tile upperLeftTile = tiles.get(0).get(0);
        Tile upperRightTile = tiles.get(0).get(3);
        return !Tile.E.equals(upperLeftTile)
                && upperLeftTile.equals(tiles.get(1).get(1))
                && upperLeftTile.equals(tiles.get(2).get(2))
                && upperLeftTile.equals(tiles.get(3).get(3))
                || !Tile.E.equals(upperRightTile)
                && upperRightTile.equals(tiles.get(1).get(2))
                && upperRightTile.equals(tiles.get(2).get(1))
                && upperRightTile.equals(tiles.get(3).get(0));
    }

    private boolean checkHorizontals(List<List<Tile>> tiles) {
        return IntStream.range(0, 4)
                .anyMatch(i -> {
                    Tile firstTile = tiles.get(i).get(0);
                    return !Tile.E.equals(firstTile) && IntStream.range(1, 4)
                            .allMatch(j -> firstTile.equals(tiles.get(i).get(j)));
                });
    }

    private boolean checkVerticals(List<List<Tile>> tiles) {
        return IntStream.range(0, 4)
                .anyMatch(i -> {
                    Tile firstTile = tiles.get(0).get(i);
                    return !Tile.E.equals(firstTile) && IntStream.range(1, 4)
                            .allMatch(j -> firstTile.equals(tiles.get(j).get(i)));
                });
    }

    private boolean checkBoxes(List<List<Tile>> tiles) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile currentTile = tiles.get(i).get(j);
                boolean validBox = !Tile.E.equals(currentTile)
                        && currentTile.equals(tiles.get(i + 1).get(j))
                        && currentTile.equals(tiles.get(i).get(j + 1))
                        && currentTile.equals(tiles.get(i + 1).get(j + 1));
                if (validBox) {
                    return true;
                }
            }
        }
        return false;
    }

}
