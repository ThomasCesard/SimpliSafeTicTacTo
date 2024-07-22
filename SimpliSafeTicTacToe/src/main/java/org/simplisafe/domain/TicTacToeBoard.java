package org.simplisafe.domain;

import java.util.List;

public class TicTacToeBoard {

    //a tic-tac-toe board is represented as a 4x4 2-D List of Tile's
    List<List<Tile>> tiles;

    //getter
    public List<List<Tile>> getTiles() {
        return tiles;
    }

    //setter
    public void setTiles(List<List<Tile>> newTiles) {
        this.tiles = newTiles;
    }

    //a tile can be X, O, or E for empty
    public enum Tile {
        X, O, E
    }

}
