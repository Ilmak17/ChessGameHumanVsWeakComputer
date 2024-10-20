package com.game.chess.pieces;

import com.game.chess.Position;
import com.game.chess.pieces.enums.Color;
import com.game.chess.pieces.enums.PieceType;
import com.game.chess.models.Board;

public class Rook extends Piece {

    public Rook(Board board, Color color, Position position) {
        super(board, color, position);
    }

    @Override
    public boolean isValidMode(Position destPosition) {
        if (getCaptured()) {
            return false;
        }

        boolean movesInLine = isMovingInLine(destPosition);
        boolean pathIsClear = isPathClear(destPosition);
        boolean destinationIsEmptyOrEnemy = !getBoard().pieceExistsAt(destPosition)
                || !getBoard().isPieceColor(destPosition, getColor());

        return movesInLine && pathIsClear && destinationIsEmptyOrEnemy;
    }

    @Override
    public String getPieceType() {
        return PieceType.ROOK.getName();
    }

    private boolean isMovingInLine(Position destPosition) {
        Position curPosition = getPosition();

        return destPosition.getCol() != curPosition.getCol() && destPosition.getRow() != curPosition.getRow();
    }

    private boolean isPathClear(Position destPosition) {
        Position curPosition = getPosition();
        Board board = getBoard();
        int start;
        int end;

        if (curPosition.getCol() == destPosition.getCol()) {
            start = Math.min(curPosition.getRow(), destPosition.getRow());
            end = Math.max(curPosition.getRow(), destPosition.getRow());

            for (int i = start + 1; i < end; i++) {
                if (board.pieceExistsAt(new Position(i, curPosition.getCol()))) {
                    return false;
                }
            }
        } else {
            start = Math.min(curPosition.getCol(), destPosition.getCol());
            end = Math.max(curPosition.getCol(), destPosition.getCol());

            for (int i = start + 1; i < end; i++) {
                if (board.pieceExistsAt(new Position(curPosition.getRow(), i))) {
                    return false;
                }
            }
        }

        return true;
    }
}
