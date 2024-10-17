package com.chess.board;

import com.chess.Position;
import com.chess.pieces.Piece;

import java.util.Arrays;


public class Board {

    private int rows;
    private int cols;
    private Piece[][] pieces;


    public Piece piece(Position position) {
        if ()
    }

    private Board(Builder builder) {
        rows = builder.rows;
        cols = builder.cols;
        pieces = builder.pieces;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public static final class Builder {
        private int rows;
        private int cols;
        private Piece[][] pieces;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder rows(int val) {
            rows = val;
            return this;
        }

        public Builder cols(int val) {
            cols = val;
            return this;
        }

        public Builder pieces(Piece[][] val) {
            pieces = val;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}
