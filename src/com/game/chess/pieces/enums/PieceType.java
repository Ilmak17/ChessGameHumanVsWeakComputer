package com.game.chess.pieces.enums;

public enum PieceType {
    PAWN("P"),
    ROOK("R"),
    KNIGHT("N"),
    BISHOP("B"),
    QUEEN("Q"),
    KING("K");

    private final String name;

    PieceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PieceType convertToType(String promotionChoice) {
        return switch (promotionChoice) {
            case "Q" -> PieceType.QUEEN;
            case "R" -> PieceType.ROOK;
            case "B" -> PieceType.BISHOP;
            case "N" -> PieceType.KNIGHT;
            default -> throw new IllegalArgumentException("Incorrect promotion choice: " + promotionChoice);
        };
    }
}
