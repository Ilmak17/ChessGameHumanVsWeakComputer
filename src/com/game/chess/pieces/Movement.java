package com.game.chess.pieces;


import java.util.List;

public interface Movement {
    boolean isValidMove(Position destPosition);

    void move(Position position);

    void forceMove(Position position);

    List<Position> getAllPossibleMoves();
}
