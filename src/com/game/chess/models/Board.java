package com.game.chess.models;

import com.game.chess.Position;
import com.game.chess.pieces.Piece;
import com.game.chess.pieces.enums.Color;

public interface Board {
    Piece getPieceByPosition(Position position);
    boolean pieceExistsAt(Position position);
    boolean isPieceColor(Position position, Color color);
    void capture(Position position);
}
