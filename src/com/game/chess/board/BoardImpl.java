package com.game.chess.board;

import com.game.chess.Position;
import com.game.chess.models.Board;
import com.game.chess.pieces.Bishop;
import com.game.chess.pieces.King;
import com.game.chess.pieces.Knight;
import com.game.chess.pieces.Pawn;
import com.game.chess.pieces.Piece;
import com.game.chess.pieces.Queen;
import com.game.chess.pieces.Rook;
import com.game.chess.pieces.enums.Color;

import java.util.ArrayList;
import java.util.List;

import static com.game.chess.pieces.enums.Color.BLACK;
import static com.game.chess.pieces.enums.Color.WHITE;

public class BoardImpl implements Board {
    public static List<Piece> pieces = new ArrayList<>();

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
        pieces.forEach(piece -> {
            if (piece.getPosition().equals(position)) {
                System.out.println("Killed: " + piece);
                piece.setCaptured(true);
                pieces.remove(piece);
            }
        });
    }

    @Override
    public boolean isKingInCheck(Color color) {
        return false;
    }

    @Override
    public boolean isSquareUnderAttack(Position position, Color color) {
        return false;
    }

    public void initBoard() {
        for (int col = 0; col < 8; col++) {
            addPiece(new Pawn(this, WHITE, new Position(col, 1)));
            addPiece(new Pawn(this, BLACK, new Position(col, 6)));
        }

        addPiece(new Rook(this, WHITE, new Position(0, 0)));
        addPiece(new Rook(this, WHITE, new Position(7, 0)));
        addPiece(new Rook(this, BLACK, new Position(0, 7)));
        addPiece(new Rook(this, BLACK, new Position(7, 7)));

        addPiece(new Knight(this, WHITE, new Position(1, 0)));
        addPiece(new Knight(this, WHITE, new Position(6, 0)));
        addPiece(new Knight(this, BLACK, new Position(1, 7)));
        addPiece(new Knight(this, BLACK, new Position(6, 7)));

        addPiece(new Bishop(this, WHITE, new Position(2, 0)));
        addPiece(new Bishop(this, WHITE, new Position(5, 0)));
        addPiece(new Bishop(this, BLACK, new Position(2, 7)));
        addPiece(new Bishop(this, BLACK, new Position(5, 7)));

        addPiece(new Queen(this, WHITE, new Position(3, 0)));
        addPiece(new Queen(this, BLACK, new Position(3, 7)));

        addPiece(new King(this, WHITE, new Position(4, 0)));
        addPiece(new King(this, BLACK, new Position(4, 7)));
    }

    private static synchronized void addPiece(Piece piece) {
        pieces.add(piece);
    }
}
