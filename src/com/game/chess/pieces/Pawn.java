package com.game.chess.pieces;

import com.game.chess.Position;
import com.game.chess.models.Board;
import com.game.chess.pieces.enums.Color;
import com.game.chess.pieces.enums.PieceType;

import java.util.Scanner;

import static com.game.chess.board.BoardImpl.pieces;
import static java.util.Objects.nonNull;

public class Pawn extends Piece {

    public Pawn(Board board, Color color, Position position) {
        super(board, color, position);
    }

    @Override
    public boolean isValidMode(Position destPosition) {
        if (getCaptured()) {
            return false;
        }

        Board board = getBoard();

        if (board.pieceExistsAt(destPosition)) {
            return false;
        }

        int direction = getColor().equals(Color.WHITE) ? 1 : -1;
        Position curPosition = getPosition();

        if (destPosition.getCol() == curPosition.getCol()) {
            if (destPosition.getRow() == curPosition.getRow() + direction) {
                return true;
            } else if ((curPosition.getRow() == 1 && getColor().equals(Color.WHITE))
                    || (curPosition.getRow() == 6 && getColor().equals(Color.BLACK))) {
                return destPosition.getRow() == curPosition.getRow() + 2 * direction
                        && !board.pieceExistsAt(
                        new Position(curPosition.getRow() + direction, destPosition.getCol()));
            }
        } else if (Math.abs(destPosition.getCol() - curPosition.getCol()) == 1
                && destPosition.getRow() == curPosition.getRow() + direction) {
            Piece pieceByPosition = board.getPieceByPosition(destPosition);

            return nonNull(pieceByPosition) && !pieceByPosition.getColor().equals(getColor());
        }

        return true;
    }

    @Override
    public void move(Position destPosition) {
        Board board = getBoard();
        if (!board.pieceExistsAt(destPosition) && !board.isPieceColor(destPosition, getColor())) {
            board.capture(destPosition);
        }
        setPosition(destPosition);
        if (shouldPromote()) {
            promote();
        }
    }

    private void promote() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose new piece (Q - Queen, R - Rook, B - Bishop, N - Knight): ");
        char promotionChoice = scanner.next().toUpperCase().charAt(0);

        while (promotionChoice != 'Q' && promotionChoice != 'R' && promotionChoice != 'B' && promotionChoice != 'N') {
            System.out.println("Incorrect input (Q - Queen, R - Rook, B - Bishop, N - Knight): ");
            promotionChoice = scanner.next().toUpperCase().charAt(0);
        }

        pieces.remove(this);

        switch (PieceType.fromChar(promotionChoice)) {
            case QUEEN -> pieces.add(new Queen(getBoard(), getColor(), getPosition()));
            case ROOK -> pieces.add(new Rook(getBoard(), getColor(), getPosition()));
            case BISHOP -> pieces.add(new Bishop(getBoard(), getColor(), getPosition()));
            case KNIGHT -> pieces.add(new Knight(getBoard(), getColor(), getPosition()));
            default -> throw new IllegalArgumentException("Incorrect promotion choice");
        }
    }

    private boolean shouldPromote() {
        return (getColor().equals(Color.WHITE) && getPosition().getRow() == 7) ||
                (getColor().equals(Color.BLACK) && getPosition().getRow() == 0);
    }

    @Override
    public String getPieceType() {
        return PieceType.PAWN.getName();
    }
}
