package com.game.chess.game.input;

import com.game.chess.pieces.Position;

public class SelectedPiece {
    private final int selectedPieceIndex;
    private final Position position;

    private SelectedPiece(Builder builder) {
        selectedPieceIndex = builder.selectedPieceIndex;
        position = builder.position;
    }

    public int getSelectedPieceIndex() {
        return selectedPieceIndex;
    }

    public Position getPosition() {
        return position;
    }

    public static final class Builder {
        private int selectedPieceIndex;
        private Position position;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder selectedPieceIndex(int val) {
            selectedPieceIndex = val;
            return this;
        }

        public Builder position(Position val) {
            position = val;
            return this;
        }

        public SelectedPiece build() {
            return new SelectedPiece(this);
        }
    }
}
