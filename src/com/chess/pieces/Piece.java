package com.chess.pieces;

import com.chess.Color;
import com.chess.Position;
import com.chess.board.Board;

public abstract class Piece {
    private Position position;
    private Color player;
    protected Board board;

    public Piece(Position position, Board board, Color player) {
        this.position = position;
        this.board = board;
        this.player = player;
    }

    public abstract boolean[][] getPossibleMoves();

    public abstract String getName();

    public boolean is
}
