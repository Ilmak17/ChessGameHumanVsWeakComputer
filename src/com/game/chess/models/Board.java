package com.game.chess.models;

import com.game.chess.Position;
import com.game.chess.pieces.Piece;

public interface Board {
    Piece getPieceByPosition(Position position);
    boolean pieceExistsAt(Position position);
    void capture(Position position);
}
