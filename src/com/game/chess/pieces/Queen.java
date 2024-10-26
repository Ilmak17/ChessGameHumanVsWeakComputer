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
        if (Boolean.TRUE.equals(getCaptured())) {
            return false;
        }

        Position curPosition = getPosition();
        int dCol = Math.abs(destPosition.getCol() - curPosition.getCol());
        int dRow = Math.abs(destPosition.getRow() - curPosition.getRow());

        boolean isDiagonalMove = dCol == dRow;
        boolean isStraightMove = dCol == 0 || dRow == 0;

        if (!isDiagonalMove && !isStraightMove) {
            return false;
        }

        return isDiagonalMove ? isValidDiagonalMove(destPosition) : isValidLinearMove(destPosition);
    }

    @Override
    public String getPieceType() {
        return PieceType.QUEEN.getName();
    }

    @Override
    public String getSymbol() {
        return getColor().equals(Color.BLACK) ? "♛" : "♕";
    }
}

