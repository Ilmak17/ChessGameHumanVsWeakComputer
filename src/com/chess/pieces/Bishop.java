package com.chess.pieces;

import com.chess.Position;

import static com.chess.board.Board.board;

public class Bishop extends Piece {
    @Override
    public boolean[][] getPossibleMoves() {
        Position position = new Position(0, 0);

        return new boolean[0][];
    }


}
