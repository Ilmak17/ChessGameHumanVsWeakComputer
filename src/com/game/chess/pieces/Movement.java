package com.game.chess.pieces;

public interface Movement {
    boolean isValidMove(Position destPosition);
    void move(Position position);
    void forceMove(Position position);
}
