package com.game.chess.pieces;


import com.game.chess.Position;
import com.game.chess.models.Board;

public abstract class DiagonalMovingPiece extends Piece {

    protected boolean isValidDiagonalMove(Position destPosition) {
        if (getCaptured()) return false;

        Board board = getBoard();
        if (board.pieceExistsAt(destPosition) && board.isPieceColor(destPosition, getColor())) {
            return false;
        }

        Position curPosition = getPosition();
        int dCol = Math.abs(destPosition.getCol() - curPosition.getCol());
        int dRow = Math.abs(destPosition.getRow() - curPosition.getRow());

        if (dCol != dRow) {
            return false;
        }

        int stepCol = Integer.compare(destPosition.getCol(), curPosition.getCol());
        int stepRow = Integer.compare(destPosition.getRow(), curPosition.getRow());

        return isPathClear(destPosition, stepRow, stepCol);
    }

    protected boolean isPathClear(Position destPosition, int stepRow, int stepCol) {
        int col = getPosition().getCol() + stepCol;
        int row = getPosition().getRow() + stepRow;

        while (row != destPosition.getRow() || col != destPosition.getCol()) {
            if (getBoard().pieceExistsAt(new Position(row, col))) {
                return false;
            }

            col += stepCol;
            row += stepRow;
        }

        return true;
    }
}
