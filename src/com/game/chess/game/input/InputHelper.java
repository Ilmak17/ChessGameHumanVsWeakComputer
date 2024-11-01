package com.game.chess.game.input;

import com.game.chess.pieces.Piece;
import com.game.chess.pieces.Position;

import java.util.List;


public class InputHelper {
    private InputHelper() {}

    public static int returnCol(char character) {
        int col = Character.toUpperCase(character) - 'A';

        return (col >= 0 && col <= 7) ? col : -1;
    }

    public static int returnRow(char number) {
        int row = number - '1';

        return (row >= 0 && row <= 7) ? row : -1;
    }

    public static int findPieceIndexByPosition(List<Piece> pieces, Position position) {
        return pieces.stream()
                .filter(piece -> piece.getPosition().equals(position))
                .findFirst()
                .map(pieces::indexOf)
                .orElseThrow(() -> new IllegalArgumentException("No piece found at the selected position."));
    }
}