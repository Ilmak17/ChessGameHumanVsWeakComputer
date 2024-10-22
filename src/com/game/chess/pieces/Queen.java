package com.game.chess.pieces;

import com.game.chess.models.Board;
import com.game.chess.pieces.enums.Color;
import com.game.chess.pieces.enums.PieceType;

public class Queen extends DiagonalMovingPiece {

    public Queen(Board board, Color color, Position position) {
        super(board, color, position);
    }

    @Override
    public boolean isValidMove(Position destPosition) {
        if (getCaptured()) {
            return false;
        }

        Position curPosition = getPosition();
        int dCol = Math.abs(destPosition.getCol() - curPosition.getCol());
        int dRow = Math.abs(destPosition.getRow()) - curPosition.getRow();

        if (dCol != dRow && dCol * dRow != 0) {
            return false;
        }

        return isValidDiagonalMove(destPosition) || isValidStraightMove(destPosition);
    }

    @Override
    public String getPieceType() {
        return PieceType.QUEEN.getName();
    }

    @Override
    public String getSymbol() {
        return getColor().equals(Color.BLACK) ? "♛" : "♕";
    }

    private boolean isValidStraightMove(Position destPosition) {
        Position curPosition = getPosition();
        if (curPosition.getCol() != destPosition.getCol() && curPosition.getRow() != destPosition.getRow()) {
            return false;
        }

        int stepCol = Integer.compare(destPosition.getCol(), curPosition.getCol());
        int stepRow = Integer.compare(destPosition.getRow(), curPosition.getRow());

        return isPathClear(destPosition, stepRow, stepCol);
    }

}
