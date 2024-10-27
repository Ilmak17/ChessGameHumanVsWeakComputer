package com.game.chess.pieces;

import com.game.chess.board.Board;
import com.game.chess.pieces.enums.Color;
import com.game.chess.pieces.enums.PieceType;

import java.util.List;
import java.util.Scanner;

import static com.game.chess.pieces.enums.PieceType.BISHOP;
import static com.game.chess.pieces.enums.PieceType.KNIGHT;
import static com.game.chess.pieces.enums.PieceType.QUEEN;
import static com.game.chess.pieces.enums.PieceType.ROOK;
import static java.util.Objects.nonNull;

public class Pawn extends Piece {
    private static final List<String> PROMOTION_TYPES = List.of(QUEEN.getName(), ROOK.getName(), BISHOP.getName(), KNIGHT.getName());
    private static final Scanner scanner = new Scanner(System.in);

    public Pawn(Board board, Color color, Position position) {
        super(board, color, position);
    }

    @Override
    public boolean isValidMove(Position destPosition) {
        Board board = getBoard();
        Position curPosition = getPosition();
        int direction = getColor() == Color.WHITE ? 1 : -1;
        int oneStepRow = curPosition.getRow() + direction;
        int twoStepRow = curPosition.getRow() + 2 * direction;

        if (destPosition.getCol() == curPosition.getCol() && destPosition.getRow() == oneStepRow) {
            return !board.pieceExistsAt(destPosition);
        }

        if (((curPosition.getRow() == 1 && getColor() == Color.WHITE) || (curPosition.getRow() == 6 && getColor() == Color.BLACK))
                && destPosition.getCol() == curPosition.getCol() && destPosition.getRow() == twoStepRow) {
            return !board.pieceExistsAt(new Position(oneStepRow, curPosition.getCol())) && !board.pieceExistsAt(destPosition);
        }


        if (Math.abs(destPosition.getCol() - curPosition.getCol()) == 1 && destPosition.getRow() == oneStepRow) {
            Piece pieceByPosition = board.getPieceByPosition(destPosition);
            return nonNull(pieceByPosition) && pieceByPosition.getColor() != getColor();
        }

        return false;
    }

    @Override
    public void move(Position destPosition) {
        Board board = getBoard();

        if (!isValidMove(destPosition)) {
            return;
        }

        if (board.pieceExistsAt(destPosition) && board.isPieceColor(destPosition, getColor())) {
            board.capture(destPosition);
        }

        setPosition(destPosition);

        if (shouldPromote()) {
            promote();
        }
    }

    @Override
    public String getPieceType() {
        return PieceType.PAWN.getName();
    }

    @Override
    public String getSymbol() {
        return getColor() == Color.BLACK ? "♟" : "♙";
    }

    private void promote() {
        System.out.println("Choose a new piece (Q - Queen, R - Rook, B - Bishop, N - Knight): ");
        String promotionChoice = String.valueOf(scanner.next().toUpperCase().charAt(0));

        while (!PROMOTION_TYPES.contains(promotionChoice)) {
            System.out.println("Choose correct piece (Q - Queen, R - Rook, B - Bishop, N - Knight): ");
            promotionChoice = String.valueOf(scanner.next().toUpperCase().charAt(0));
        }

        List<Piece> pieces = getBoard().getPieces();
        pieces.remove(this);

        switch (PieceType.convertToType(promotionChoice)) {
            case QUEEN -> pieces.add(new Queen(getBoard(), getColor(), getPosition()));
            case ROOK -> pieces.add(new Rook(getBoard(), getColor(), getPosition()));
            case BISHOP -> pieces.add(new Bishop(getBoard(), getColor(), getPosition()));
            case KNIGHT -> pieces.add(new Knight(getBoard(), getColor(), getPosition()));
            default -> throw new IllegalStateException("Unexpected promotion choice: " + promotionChoice);
        }
    }

    private boolean shouldPromote() {
        return (getColor() == Color.WHITE && getPosition().getRow() == 7) || (getColor() == Color.BLACK && getPosition().getRow() == 0);
    }
}
