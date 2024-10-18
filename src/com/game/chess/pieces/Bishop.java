package com.game.chess.pieces;

import com.game.chess.Position;
import com.game.chess.pieces.enums.PieceType;

public class Bishop extends Piece {



    @Override
    public boolean isValidMode(Position destPosition) {
        if (getCaptured()) return false;


        return true;
    }

    @Override
    public String getPieceType() {
        return PieceType.BISHOP.getName();
    }
}
