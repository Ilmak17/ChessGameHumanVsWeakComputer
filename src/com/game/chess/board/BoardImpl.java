package com.game.chess.board;

import com.game.chess.Position;
import com.game.chess.models.Board;
import com.game.chess.pieces.Piece;
import com.game.chess.pieces.enums.Color;

import java.util.List;

public class BoardImpl implements Board {
    public static List<Piece> pieces;

    @Override
    public Piece getPieceByPosition(Position position) {
        return null;
    }

    @Override
    public boolean pieceExistsAt(Position position) {
        return false;
    }

    @Override
    public boolean isPieceColor(Position position, Color color) {
        return false;
    }

    @Override
    public void capture(Position position) {

    }
}
