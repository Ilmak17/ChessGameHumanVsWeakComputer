package com.chess.pieces;

import com.chess.Color;
import com.chess.Figure;
import com.chess.Position;
import com.chess.board.Board;
import com.chess.utils.ChessUtils;

public class Rook extends Piece {

    public Rook(Position position, Board board, Color player) {
        super(position, board, player);
    }

    @Override
    public boolean[][] getPossibleMoves() {
        boolean[][] matrix = new boolean[board.getRows()][board.getCols()];

        while (ChessUtils.positionExists())

        return new boolean[0][];
    }

    @Override
    public String getName() {
        return Figure.ROOK.getName();
    }
}
