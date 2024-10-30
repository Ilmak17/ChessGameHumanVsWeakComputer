package com.game.chess.board;

import com.game.chess.pieces.Bishop;
import com.game.chess.pieces.King;
import com.game.chess.pieces.Knight;
import com.game.chess.pieces.Pawn;
import com.game.chess.pieces.Piece;
import com.game.chess.pieces.Position;
import com.game.chess.pieces.Queen;
import com.game.chess.pieces.Rook;
import com.game.chess.pieces.enums.Color;

import java.util.List;

public class BoardInitializer {
    private BoardInitializer() {
    }

    public static void initializeBoard(List<Piece> pieces, Board board) {
        for (int col = 0; col < 8; col++) {
            pieces.add(new Pawn(board, Color.WHITE, new Position(1, col)));
            pieces.add(new Pawn(board, Color.BLACK, new Position(6, col)));
        }

        addMainPieces(pieces, board, Color.WHITE, 0);
        addMainPieces(pieces, board, Color.BLACK, 7);
    }

    private static void addMainPieces(List<Piece> pieces, Board board, Color color, int row) {
        pieces.add(new Rook(board, color, new Position(row, 0)));
        pieces.add(new Knight(board, color, new Position(row, 1)));
        pieces.add(new Bishop(board, color, new Position(row, 2)));
        pieces.add(new Queen(board, color, new Position(row, 3)));
        pieces.add(new King(board, color, new Position(row, 4)));
        pieces.add(new Bishop(board, color, new Position(row, 5)));
        pieces.add(new Knight(board, color, new Position(row, 6)));
        pieces.add(new Rook(board, color, new Position(row, 7)));
    }
}