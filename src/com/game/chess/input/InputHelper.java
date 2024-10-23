package com.game.chess.input;

import com.game.chess.pieces.Piece;
import com.game.chess.pieces.Position;

import java.util.List;

public class InputHelper {

    public static int returnCol(char colChar) {
        return Character.toUpperCase(colChar) - 'A';
    }

    public static int returnRow(char rowChar) {
        return '8' - rowChar;
    }

    public static int findPieceIndexByPosition(List<Piece> pieces, Position position) {
        return pieces.stream()
                .filter(piece -> piece.getPosition().equals(position))
                .findFirst()
                .map(pieces::indexOf)
                .orElseThrow(() -> new IllegalArgumentException("No piece found at the selected position."));
    }
}