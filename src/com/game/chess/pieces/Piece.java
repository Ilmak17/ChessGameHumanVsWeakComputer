package com.game.chess.pieces;

import com.game.chess.pieces.enums.Color;
import com.game.chess.board.Board;

public abstract class Piece implements Movement {

    private Position position;
    private final Color color;
    private boolean isCaptured = false;
    private final Board board;
    private boolean moved = false;

    Piece(Board board, Color color, Position position) {
        this.board = board;
        this.color = color;
        this.position = position;
    }

    @Override
    public void move(Position position) {
        if (isCaptured) return;
        if (isDestinationAvailable(position)) {
            setPosition(position);
            moved = true;
        }
    }

    @Override
    public void forceMove(Position position) {
        if (isCaptured) return;
        setPosition(position);
        moved = true;
    }

    public abstract String getPieceType();

    public abstract String getSymbol();

    protected boolean isDestinationAvailable(Position destPosition) {
        return !getBoard().pieceExistsAt(destPosition) || getBoard().isPieceColor(destPosition, getColor());
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

    public boolean isCaptured() {
        return isCaptured;
    }

    public void setCaptured(boolean captured) {
        isCaptured = captured;
    }

    public Board getBoard() {
        return board;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }
}
