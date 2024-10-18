package com.game.chess.models;

import com.game.chess.Position;

public interface Movement {

    boolean isValidMode(Position destPosition);

    void move(Position position);
}
