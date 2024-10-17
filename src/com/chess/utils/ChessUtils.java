package com.chess.utils;

import com.chess.Position;

public class ChessUtils {

    public static boolean positionExists(Position position) {
        int row = position.getRow();
        int col = position.getCol();

        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }



}
