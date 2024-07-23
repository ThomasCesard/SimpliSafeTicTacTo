package org.simplisafe.service;

import org.junit.jupiter.api.Test;
import org.simplisafe.domain.TicTacToeBoard;
import org.simplisafe.domain.TicTacToeBoard.Tile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeServiceTest {

    private final TicTacToeService ticTacToeService = new TicTacToeService();

    @Test
    void testGetWinner_horizontal() {
        List<List<Tile>> tiles = new ArrayList<>();
        tiles.add(List.of(Tile.X, Tile.X, Tile.X, Tile.X));
        tiles.add(List.of(Tile.E, Tile.E, Tile.E, Tile.E));
        tiles.add(List.of(Tile.E, Tile.E, Tile.E, Tile.E));
        tiles.add(List.of(Tile.E, Tile.E, Tile.E, Tile.E));
        TicTacToeBoard board = new TicTacToeBoard();
        board.setTiles(tiles);

        assertEquals(Tile.X, ticTacToeService.getWinner(board));
    }

    @Test
    void testGetWinner_vertical() {
        List<List<Tile>> tiles = new ArrayList<>();
        tiles.add(List.of(Tile.E, Tile.E, Tile.E, Tile.O));
        tiles.add(List.of(Tile.E, Tile.E, Tile.E, Tile.O));
        tiles.add(List.of(Tile.E, Tile.E, Tile.E, Tile.O));
        tiles.add(List.of(Tile.E, Tile.E, Tile.E, Tile.O));
        TicTacToeBoard board = new TicTacToeBoard();
        board.setTiles(tiles);

        assertEquals(Tile.O, ticTacToeService.getWinner(board));
    }

    @Test
    void testGetWinner_diagonalTopLeft() {
        List<List<Tile>> tiles = new ArrayList<>();
        tiles.add(List.of(Tile.X, Tile.E, Tile.E, Tile.E));
        tiles.add(List.of(Tile.E, Tile.X, Tile.E, Tile.E));
        tiles.add(List.of(Tile.E, Tile.E, Tile.X, Tile.E));
        tiles.add(List.of(Tile.E, Tile.E, Tile.E, Tile.X));
        TicTacToeBoard board = new TicTacToeBoard();
        board.setTiles(tiles);

        assertEquals(Tile.X, ticTacToeService.getWinner(board));
    }

    @Test
    void testGetWinner_diagonalTopRight() {
        List<List<Tile>> tiles = new ArrayList<>();
        tiles.add(List.of(Tile.E, Tile.E, Tile.E, Tile.O));
        tiles.add(List.of(Tile.E, Tile.E, Tile.O, Tile.E));
        tiles.add(List.of(Tile.E, Tile.O, Tile.E, Tile.E));
        tiles.add(List.of(Tile.O, Tile.E, Tile.E, Tile.E));
        TicTacToeBoard board = new TicTacToeBoard();
        board.setTiles(tiles);

        assertEquals(Tile.O, ticTacToeService.getWinner(board));
    }

    @Test
    void testGetWinner_corners() {
        List<List<Tile>> tiles = new ArrayList<>();
        tiles.add(List.of(Tile.X, Tile.E, Tile.E, Tile.X));
        tiles.add(List.of(Tile.E, Tile.E, Tile.E, Tile.E));
        tiles.add(List.of(Tile.E, Tile.E, Tile.E, Tile.E));
        tiles.add(List.of(Tile.X, Tile.E, Tile.E, Tile.X));
        TicTacToeBoard board = new TicTacToeBoard();
        board.setTiles(tiles);

        assertEquals(Tile.X, ticTacToeService.getWinner(board));
    }

    @Test
    void testGetWinner_box() {
        List<List<Tile>> tiles = new ArrayList<>();
        tiles.add(List.of(Tile.E, Tile.E, Tile.E, Tile.E));
        tiles.add(List.of(Tile.E, Tile.E, Tile.E, Tile.E));
        tiles.add(List.of(Tile.E, Tile.E, Tile.O, Tile.O));
        tiles.add(List.of(Tile.E, Tile.E, Tile.O, Tile.O));
        TicTacToeBoard board = new TicTacToeBoard();
        board.setTiles(tiles);

        assertEquals(Tile.O, ticTacToeService.getWinner(board));
    }

    @Test
    void testGetWinner_false() {
        List<List<Tile>> tiles = new ArrayList<>();
        tiles.add(List.of(Tile.X, Tile.X, Tile.X, Tile.E));
        tiles.add(List.of(Tile.X, Tile.O, Tile.X, Tile.O));
        tiles.add(List.of(Tile.X, Tile.O, Tile.X, Tile.O));
        tiles.add(List.of(Tile.E, Tile.O, Tile.O, Tile.O));
        TicTacToeBoard board = new TicTacToeBoard();
        board.setTiles(tiles);

        assertEquals(Tile.E, ticTacToeService.getWinner(board));
    }

    @Test
    void canMove_true() {
        List<List<Tile>> tiles = new ArrayList<>();
        tiles.add(List.of(Tile.X, Tile.X, Tile.X, Tile.O));
        tiles.add(List.of(Tile.X, Tile.O, Tile.X, Tile.O));
        tiles.add(List.of(Tile.X, Tile.O, Tile.X, Tile.O));
        tiles.add(List.of(Tile.E, Tile.O, Tile.O, Tile.O));
        TicTacToeBoard board = new TicTacToeBoard();
        board.setTiles(tiles);

        assertTrue(ticTacToeService.canMove(board));
    }

    @Test
    void canMove_false() {
        List<List<Tile>> tiles = new ArrayList<>();
        tiles.add(List.of(Tile.X, Tile.X, Tile.X, Tile.O));
        tiles.add(List.of(Tile.X, Tile.O, Tile.X, Tile.O));
        tiles.add(List.of(Tile.X, Tile.O, Tile.X, Tile.O));
        tiles.add(List.of(Tile.X, Tile.O, Tile.O, Tile.O));
        TicTacToeBoard board = new TicTacToeBoard();
        board.setTiles(tiles);

        assertFalse(ticTacToeService.canMove(board));
    }

    @Test
    void isGameOver_getWinner() {
        List<List<Tile>> tiles = new ArrayList<>();
        tiles.add(List.of(Tile.X, Tile.X, Tile.X, Tile.O));
        tiles.add(List.of(Tile.X, Tile.O, Tile.X, Tile.O));
        tiles.add(List.of(Tile.X, Tile.O, Tile.X, Tile.O));
        tiles.add(List.of(Tile.E, Tile.O, Tile.O, Tile.O));
        TicTacToeBoard board = new TicTacToeBoard();
        board.setTiles(tiles);

        assertTrue(ticTacToeService.isGameOver(board));
    }

    @Test
    void isGameOver_canNotMove() {
        List<List<Tile>> tiles = new ArrayList<>();
        tiles.add(List.of(Tile.X, Tile.X, Tile.X, Tile.O));
        tiles.add(List.of(Tile.X, Tile.O, Tile.X, Tile.X));
        tiles.add(List.of(Tile.O, Tile.O, Tile.X, Tile.O));
        tiles.add(List.of(Tile.X, Tile.O, Tile.O, Tile.O));
        TicTacToeBoard board = new TicTacToeBoard();
        board.setTiles(tiles);

        assertTrue(ticTacToeService.isGameOver(board));
    }

    @Test
    void isGameOver_false() {
        List<List<Tile>> tiles = new ArrayList<>();
        tiles.add(List.of(Tile.X, Tile.X, Tile.X, Tile.E));
        tiles.add(List.of(Tile.X, Tile.O, Tile.X, Tile.O));
        tiles.add(List.of(Tile.X, Tile.O, Tile.X, Tile.O));
        tiles.add(List.of(Tile.E, Tile.O, Tile.O, Tile.O));
        TicTacToeBoard board = new TicTacToeBoard();
        board.setTiles(tiles);

        assertFalse(ticTacToeService.isGameOver(board));
    }

}