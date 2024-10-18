package com.game.chess.pieces;

import com.game.chess.Position;
import com.game.chess.enums.PieceType;
import com.game.chess.models.Board;

public class Rook extends Piece {


    @Override
    public boolean isValidMode(Position destPosition) {
        if (getCaptured()) {
            return false;
        }

        boolean movesInLine = isMovingInLine(destPosition);
        boolean pathIsClear = !isPathUnclear(destPosition);
        boolean destinationIsEmptyOrEnemy = !getBoard().pieceExistsAt(destPosition);

        return movesInLine && pathIsClear && destinationIsEmptyOrEnemy;
    }

    private boolean isMovingInLine(Position destPosition) {
        Position curPosition = getPosition();

        return destPosition.getCol() != curPosition.getCol() && destPosition.getRow() != curPosition.getRow();
    }

    private boolean isPathUnclear(Position destPosition) {
        Position curPosition = getPosition();
        Board board = getBoard();
        int start, end;

        if (curPosition.getCol() == destPosition.getCol()) {
            start = Math.min(curPosition.getRow(), destPosition.getRow());
            end = Math.max(curPosition.getRow(), destPosition.getRow());

            for (int i = start + 1; i < end; i++) {
                if (board.pieceExistsAt(new Position(i, curPosition.getCol()))) {
                    return true;
                }
            }
        } else {
            start = Math.min(curPosition.getCol(), destPosition.getCol());
            end = Math.max(curPosition.getCol(), destPosition.getCol());

            for (int i = start + 1; i < end; i++) {
                if (board.pieceExistsAt(new Position(curPosition.getRow(), i))) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String getPieceType() {
        return PieceType.ROOK.getName();
    }
}
