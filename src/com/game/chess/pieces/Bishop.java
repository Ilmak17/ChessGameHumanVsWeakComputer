package com.game.chess.pieces;

import com.game.chess.Position;
import com.game.chess.models.Board;
import com.game.chess.pieces.enums.PieceType;

public class Bishop extends Piece {

    @Override
    public boolean isValidMode(Position destPosition) {
        if (getCaptured()) return false;

        Board board = getBoard();
        if (board.pieceExistsAt(destPosition) || board.isPieceColor(destPosition, getColor())) {
            return false;
        }

        Position curPosition = getPosition();
        int dCol = Math.abs(destPosition.getCol() - curPosition.getCol());
        int dRow = Math.abs(destPosition.getRow() - curPosition.getCol());

        if (dCol != dRow) {
            return false;
        }

        int stepCol = destPosition.getCol() > curPosition.getCol() ? 1 : -1;
        int stepRow = destPosition.getRow() > curPosition.getRow() ? 1 : -1;

        return !isPathUnclear(destPosition, stepCol, stepRow);
    }

    private boolean isPathUnclear(Position destPosition, int stepRow, int stepCol) {
        int col = getPosition().getCol() + stepCol;
        int row = getPosition().getRow() + stepCol;

        while (row != destPosition.getRow() && col != destPosition.getCol()) {
            if (getBoard().pieceExistsAt(new Position(row, col))) {
                return true;
            }

            col += stepCol;
            row += stepRow;
        }

        return false;
    }

    @Override
    public String getPieceType() {
        return PieceType.BISHOP.getName();
    }
}
