package com.game.chess.pieces;

import com.game.chess.board.Board;
import com.game.chess.pieces.enums.Color;
import com.game.chess.pieces.enums.PieceType;

public class Queen extends SlidingPiece {

    public Queen(Board board, Color color, Position position) {
        super(board, color, position);
    }

    @Override
    public boolean isValidMove(Position destPosition) {
        return isValidDiagonalMove(destPosition) || isValidLinearMove(destPosition);
    }

    @Override
    public String getPieceType() {
        return PieceType.QUEEN.getName();
    }

    @Override
    public String getSymbol() {
        return getColor() == Color.BLACK ? "♛" : "♕";
    }
}
