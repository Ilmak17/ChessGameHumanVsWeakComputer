package com.game.chess.pieces;

import com.game.chess.Position;
import com.game.chess.models.Board;
import com.game.chess.pieces.enums.Color;
import com.game.chess.pieces.enums.PieceType;

public class Bishop extends DiagonalMovingPiece {

    public Bishop(Board board, Color color, Position position) {
        super(board, color, position);
    }

    @Override
    public boolean isValidMode(Position destPosition) {
        return isValidDiagonalMove(destPosition);
    }

    @Override
    public String getPieceType() {
        return PieceType.BISHOP.getName();
    }
}
