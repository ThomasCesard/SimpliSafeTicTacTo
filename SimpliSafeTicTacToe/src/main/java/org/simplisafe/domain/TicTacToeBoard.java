package org.simplisafe.domain;

import java.util.List;

public class TicTacToeBoard {

    //a tic-tac-toe board is represented as a 2-D List of Tile's
    List<List<Tile>> tiles;

    //defines the size of the square tic-tac-toe board
    final static int boardSize = 4;

    //getters
    public List<List<Tile>> getTiles() {
        return tiles;
    }

    public int getBoardSize() {
        return boardSize;
    }

    //setters
    public void setTiles(List<List<Tile>> newTiles) {
        this.tiles = newTiles;
    }

    //a tile can be X, O, or E for empty
    public enum Tile {
        X, O, E
    }

}
