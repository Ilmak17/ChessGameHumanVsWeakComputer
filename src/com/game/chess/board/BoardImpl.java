package com.game.chess.board;

import com.game.chess.pieces.Position;
import com.game.chess.pieces.Bishop;
import com.game.chess.pieces.King;
import com.game.chess.pieces.Knight;
import com.game.chess.pieces.Pawn;
import com.game.chess.pieces.Piece;
import com.game.chess.pieces.Queen;
import com.game.chess.pieces.Rook;
import com.game.chess.pieces.enums.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.game.chess.pieces.enums.Color.BLACK;
import static com.game.chess.pieces.enums.Color.WHITE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class BoardImpl implements Board {
    private int checkingKingIdx;
    private int threateningPieceIdx;
    private final List<Piece> pieces = new ArrayList<>();

    public BoardImpl() {
        initBoard();
    }

    @Override
    public Piece getPieceByPosition(Position position) {
        return pieces.stream()
                .filter(piece -> piece.getPosition().equals(position))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean pieceExistsAt(Position position) {
        return nonNull(getPieceByPosition(position));
    }

    @Override
    public boolean isPieceColor(Position position, Color color) {
        Piece piece = getPieceByPosition(position);

        return nonNull(piece) && piece.getColor().equals(color);
    }

    @Override
    public void capture(Position position) {
        Iterator<Piece> iterator = pieces.iterator();
        while (iterator.hasNext()) {
            Piece piece = iterator.next();
            if (piece.getPosition().equals(position)) {
                System.out.println("Killed: " + piece);
                piece.setCaptured(true);
                iterator.remove();
            }
        }
    }

    @Override
    public boolean isKingInCheck(Color color) {
        Piece checkedKing = pieces.stream()
                .filter(piece -> piece.getColor().equals(color) && piece instanceof King)
                .findFirst()
                .orElse(null);

        if (isNull(checkedKing)) {
            return false;
        }

        Piece threateningPiece = pieces.stream()
                .filter(piece -> !piece.getColor().equals(color) && piece.isValidMove(checkedKing.getPosition()))
                .findFirst()
                .orElse(null);

        if (nonNull(threateningPiece)) {
            threateningPieceIdx = pieces.indexOf(threateningPiece);
            checkingKingIdx = pieces.indexOf(checkedKing);

            return true;
        }

        return false;
    }

    @Override
    public boolean isSquareUnderAttack(Position position, Color color) {
        return pieces.stream()
                .filter(piece -> !piece.getColor().equals(color))
                .anyMatch(piece -> piece.isValidMove(position));
    }

    @Override
    public boolean canPreventCheckmate() {
        Piece threateningPiece = pieces.get(threateningPieceIdx);

        return pieces.stream()
                .filter(piece -> !piece.getColor().equals(threateningPiece.getColor()))
                .anyMatch(piece -> piece.isValidMove(threateningPiece.getPosition()));
    }

    @Override
    public boolean isCheckmate(Color color) {
        if (!isKingInCheck(color)) return false;

        for (int col = 0; col <= 7; col++) {
            for (int row = 0; row <= 7; row++) {
                if (pieces.get(checkingKingIdx).isValidMove(new Position(row, col))) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public List<Piece> getPieces() {
        return pieces;
    }

    public void initBoard() {
        for (int col = 0; col < 8; col++) {
            pieces.add(new Pawn(this, WHITE, new Position(1, col)));
            pieces.add(new Pawn(this, BLACK, new Position(6, col)));
        }

        pieces.add(new Rook(this, WHITE, new Position(0, 0)));
        pieces.add(new Knight(this, WHITE, new Position(0, 1)));
        pieces.add(new Bishop(this, WHITE, new Position(0, 2)));
        pieces.add(new Queen(this, WHITE, new Position(0, 3)));
        pieces.add(new King(this, WHITE, new Position(0, 4)));
        pieces.add(new Bishop(this, WHITE, new Position(0, 5)));
        pieces.add(new Knight(this, WHITE, new Position(0, 6)));
        pieces.add(new Rook(this, WHITE, new Position(0, 7)));

        pieces.add(new Rook(this, BLACK, new Position(7, 0)));
        pieces.add(new Knight(this, BLACK, new Position(7, 1)));
        pieces.add(new Bishop(this, BLACK, new Position(7, 2)));
        pieces.add(new Queen(this, BLACK, new Position(7, 3)));
        pieces.add(new King(this, BLACK, new Position(7, 4)));
        pieces.add(new Bishop(this, BLACK, new Position(7, 5)));
        pieces.add(new Knight(this, BLACK, new Position(7, 6)));
        pieces.add(new Rook(this, BLACK, new Position(7, 7)));
    }
}
