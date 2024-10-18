package com.game.chess.pieces;

import com.game.chess.Position;
import com.game.chess.enums.PieceType;

public class Bishop extends Piece {



    @Override
    public boolean isValidMode(Position position) {
        if (getCaptured()) return false;


        return true;
    }

    @Override
    public String getPieceType() {
        return PieceType.BISHOP.getName();
    }
}
