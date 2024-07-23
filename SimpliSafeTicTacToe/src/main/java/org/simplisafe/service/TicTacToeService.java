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
     * @param board a valid tic-tac-toe board.
     * @return the Tile of the winner, or an empty Tile if there is no winner.
     */
    public Tile getWinner(TicTacToeBoard board) {
        if (board.getBoardSize() <= 0) {
            return Tile.E;
        }

        Tile winner = checkCorners(board);
        if (Tile.E.equals(winner)) {
            winner = checkDiagonals(board);
        }
        if (Tile.E.equals(winner)) {
            winner = checkVerticals(board);
        }
        if (Tile.E.equals(winner)) {
            winner = checkHorizontals(board);
        }
        if (Tile.E.equals(winner)) {
            winner = checkBoxes(board);
        }
        return winner;
    }

    /**
     * Compute if a valid move can be made in a game of tic-tac-toe.
     * A valid move can be made if there is an empty tile on the board,
     * regardless if the game has been won or not.
     *
     * @param board a valid tic-tac-toe board.
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
     * @param board a valid tic-tac-toe board.
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver(TicTacToeBoard board) {
        return !canMove(board) || !Tile.E.equals(getWinner(board));
    }

    private Tile checkCorners (TicTacToeBoard board) {
        int boardSize = board.getBoardSize();
        List<List<Tile>> tiles = board.getTiles();
        Tile firstTile = tiles.get(0).get(0);

        boolean isWinner = !Tile.E.equals(firstTile)
                && firstTile.equals(tiles.get(0).get(boardSize - 1))
                && firstTile.equals(tiles.get(boardSize - 1).get(0))
                && firstTile.equals(tiles.get(boardSize - 1).get(boardSize - 1));
        return isWinner ? firstTile : Tile.E;
    }

    private Tile checkDiagonals(TicTacToeBoard board) {
        Tile upperLeftTile = checkUpperLeftDiagonal(board);
        return !Tile.E.equals(upperLeftTile) ? upperLeftTile : checkUpperRightDiagonal(board);
    }

    private Tile checkUpperLeftDiagonal(TicTacToeBoard board) {
        List<List<Tile>> tiles = board.getTiles();
        Tile upperLeftTile = tiles.get(0).get(0);

        boolean upperLeftWinner = !Tile.E.equals(upperLeftTile) && IntStream.range(1, board.getBoardSize())
                .allMatch(i -> upperLeftTile.equals(tiles.get(i).get(i)));

        return upperLeftWinner ? upperLeftTile : Tile.E;
    }

    private Tile checkUpperRightDiagonal(TicTacToeBoard board) {
        List<List<Tile>> tiles = board.getTiles();
        Tile upperRightTile = tiles.get(0).get(board.getBoardSize() - 1);

        boolean upperRightWinner = !Tile.E.equals(upperRightTile) && IntStream.range(1, board.getBoardSize())
                .allMatch(i -> upperRightTile.equals(tiles.get(i).get(board.getBoardSize() - 1 - i)));

        return upperRightWinner ? upperRightTile : Tile.E;
    }

    private Tile checkHorizontals(TicTacToeBoard board) {
        List<List<Tile>> tiles = board.getTiles();
        return IntStream.range(0, board.getBoardSize())
                .filter(i -> {
                    Tile firstTile = tiles.get(i).get(0);
                    return !Tile.E.equals(firstTile) && IntStream.range(1, board.getBoardSize())
                            .allMatch(j -> firstTile.equals(tiles.get(i).get(j)));
                })
                .mapToObj(i -> tiles.get(i).get(0))
                .findAny().orElse(Tile.E);
    }

    private Tile checkVerticals(TicTacToeBoard board) {
        List<List<Tile>> tiles = board.getTiles();
        return IntStream.range(0, board.getBoardSize())
                .filter(i -> {
                    Tile firstTile = tiles.get(0).get(i);
                    return !Tile.E.equals(firstTile) && IntStream.range(1, board.getBoardSize())
                            .allMatch(j -> firstTile.equals(tiles.get(j).get(i)));
                })
                .mapToObj(i -> tiles.get(0).get(i))
                .findAny().orElse(Tile.E);
    }

    private Tile checkBoxes(TicTacToeBoard board) {
        List<List<Tile>> tiles = board.getTiles();
        for (int i = 0; i < board.getBoardSize() - 1; i++) {
            for (int j = 0; j < board.getBoardSize() - 1; j++) {
                Tile currentTile = tiles.get(i).get(j);
                boolean validBox = !Tile.E.equals(currentTile)
                        && currentTile.equals(tiles.get(i + 1).get(j))
                        && currentTile.equals(tiles.get(i).get(j + 1))
                        && currentTile.equals(tiles.get(i + 1).get(j + 1));
                if (validBox) {
                    return currentTile;
                }
            }
        }
        return Tile.E;
    }

}
