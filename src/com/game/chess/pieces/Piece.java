package com.game.chess.pieces;

import com.game.chess.enums.Color;
import com.game.chess.Position;
import com.game.chess.models.Board;
import com.game.chess.models.Movement;

public abstract class Piece implements Movement {

    private Position position;
    private Color color;
    private Boolean isCaptured;
    private Board board;

    @Override
    public void move(Position position) {
        if (board.pieceExistsAt(position)) return;

        setPosition(position);
    }

    public abstract String getPieceType();

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
}
