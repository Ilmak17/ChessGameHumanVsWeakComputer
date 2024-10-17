package com.chess;

public enum Figure {
    PAWN("P"),
    BISHOP("B"),
    ROOK("R"),
    KNIGHT("N"),
    QUEEN("Q"),
    KING("K");

    private final String name;

    Figure(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
