package com.game.chess.pieces;

import com.game.chess.pieces.enums.Color;
import com.game.chess.models.Board;
import com.game.chess.models.Movement;

public abstract class Piece implements Movement {

    private Position position;
    private Color color;
    private Boolean isCaptured;
    private Board board;
    private boolean moved;

    Piece(Board board, Color color, Position position) {
        this.board = board;
        this.color = color;
        this.position = position;
    }

    @Override
    public void move(Position position) {
        if (board.pieceExistsAt(position)) return;

        setPosition(position);
    }

    @Override
    public void forceMove(Position position) {
        setPosition(position);
    }

    public abstract String getPieceType();

    public abstract String getSymbol();

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
