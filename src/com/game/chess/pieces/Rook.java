package com.game.chess.pieces;

import com.game.chess.pieces.enums.Color;
import com.game.chess.pieces.enums.PieceType;
import com.game.chess.board.Board;

public class Rook extends SlidingPiece {

    public Rook(Board board, Color color, Position position) {
        super(board, color, position);
    }

    @Override
    public boolean isValidMove(Position destPosition) {
        if (Boolean.TRUE.equals(getCaptured())) {
            return false;
        }

        return isValidLinearMove(destPosition);
    }

    @Override
    public String getPieceType() {
        return PieceType.ROOK.getName();
    }

    @Override
    public String getSymbol() {
        return getColor().equals(Color.BLACK) ? "♜" : "♖";
    }
}
