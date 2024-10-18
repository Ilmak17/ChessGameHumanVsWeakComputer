package com.game.chess.pieces;

import com.game.chess.Position;
import com.game.chess.models.Board;
import com.game.chess.pieces.enums.PieceType;

public class Knight extends Piece {

    @Override
    public boolean isValidMode(Position destPosition) {
        if (getCaptured()) {
            return false;
        }
        Board board = getBoard();

        if (board.pieceExistsAt(destPosition) || board.isPieceColor(destPosition, getColor())) {
            return false;
        }

        Position curPosition = getPosition();
        int dCol = Math.abs(destPosition.getCol() - curPosition.getCol());
        int dRow = Math.abs(destPosition.getRow() - curPosition.getRow());

        return (dCol == 1 && dRow == 2) || (dCol == 2 && dRow == 1);
    }

    @Override
    public String getPieceType() {
        return PieceType.KNIGHT.getName();
    }
}
