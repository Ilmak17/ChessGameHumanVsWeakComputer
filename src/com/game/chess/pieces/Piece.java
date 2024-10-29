package com.game.chess.pieces;

import com.game.chess.pieces.enums.Color;
import com.game.chess.board.Board;

import static java.util.Objects.isNull;

public abstract class Piece implements Movement {

    private Position position;
    private Color color;
    private Boolean isCaptured = Boolean.FALSE;
    private Board board;
    private boolean moved;

    Piece(Board board, Color color, Position position) {
        this.board = board;
        this.color = color;
        this.position = position;
    }

    @Override
    public void move(Position position) {
        validateMove(position);

        if (board.pieceExistsAt(position) && board.isNotPieceColor(position, getColor())) {
            board.capture(position);
        }

        setPosition(position);
        setMoved(true);
    }

    private void validateMove(Position position) {
        if (Boolean.TRUE.equals(isCaptured)) {
            throw new IllegalArgumentException("Invalid move: Piece is captured. Please try again.");
        }

        if (!isValidMove(position)) {
            throw new IllegalArgumentException("Invalid move: Piece move is invalid. Please try again.");
        }

        if (isMoveLeavingKingInCheck(position)) {
            throw new IllegalArgumentException("Invalid move: Piece move is in check. Please try again.");
        }
    }

    @Override
    public void forceMove(Position position) {
        setPosition(position);
    }

    public abstract boolean canAttack(Position destPosition);

    public abstract String getPieceType();

    public abstract String getSymbol();

    protected boolean isDestinationAvailable(Position destPosition) {
        Piece targetPiece = board.getPieceByPosition(destPosition);

        return isNull(targetPiece) || !targetPiece.getColor().equals(getColor());
    }

    private boolean isMoveLeavingKingInCheck(Position position) {
        Position originalPosition = getPosition();
        Piece capturedPiece = executeTemporaryMove(position);
        boolean kingInCheck = board.isKingInCheck(getColor());
        revertTemporaryMove(originalPosition, capturedPiece);

        return kingInCheck;
    }

    private Piece executeTemporaryMove(Position position) {
        Piece capturedPiece = null;
        if (board.pieceExistsAt(position) && board.isNotPieceColor(position, getColor())) {
            capturedPiece = board.getPieceByPosition(position);
            capturedPiece.setCaptured(true);
            board.getPieces().remove(capturedPiece);
        }
        setPosition(position);
        return capturedPiece;
    }

    private void revertTemporaryMove(Position originalPosition, Piece capturedPiece) {
        setPosition(originalPosition);
        if (capturedPiece != null) {
            capturedPiece.setCaptured(false);
            board.getPieces().add(capturedPiece);
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Boolean getCaptured() {
        return isCaptured;
    }

    public void setCaptured(Boolean captured) {
        isCaptured = captured;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }
}
