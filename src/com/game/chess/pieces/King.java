package com.game.chess.pieces;

import com.game.chess.models.Board;
import com.game.chess.pieces.enums.Color;
import com.game.chess.pieces.enums.PieceType;

import java.util.List;

import static java.util.Objects.isNull;

public class King extends Piece {

    private static int rookId;

    public King(Board board, Color color, Position position) {
        super(board, color, position);
    }

    @Override
    public boolean isValidMove(Position destPosition) {
        if (getCaptured()) {
            return false;
        }

        if (getBoard().pieceExistsAt(destPosition) || getBoard().isPieceColor(destPosition, getColor())) {
            return false;
        }

        Position curPosition = getPosition();

        int dCol = Math.abs(destPosition.getCol() - curPosition.getCol());
        int dRow = Math.abs(destPosition.getRow() - curPosition.getRow());

        if ((dCol == 2 || dCol == 3) && dRow == 0) {
            return canDoCastling(destPosition);
        }

        return dCol <= 1 && dRow <= 1;
    }

    @Override
    public void move(Position destPosition) {
        if (canDoCastling(destPosition) && isValidMove(destPosition)) {

            if (destPosition.getCol() == 0) {
                int kingNewCol;
                int rookNewCol;

                if (destPosition.getCol() == 0) {
                    kingNewCol = 2;
                    rookNewCol = 3;
                } else {
                    kingNewCol = 6;
                    rookNewCol = 5;
                }

                List<Piece> pieces = getBoard().getPieces();

                forceMove(new Position(kingNewCol, destPosition.getRow()));
                pieces.get(rookId).forceMove(new Position(rookNewCol, destPosition.getRow()));

                setMoved(true);
                pieces.get(rookId).setMoved(true);
            }

            return;
        }

        super.move(destPosition);
    }

    @Override
    public String getPieceType() {
        return PieceType.KING.getName();
    }

    @Override
    public String getSymbol() {
        return getColor().equals(Color.BLACK) ? "♚" : "♔";
    }

    private boolean canDoCastling(Position destPosition) {
        Board board = getBoard();
        if (board.isKingInCheck(getColor())) {
            return false;
        }


        Position curPosition = getPosition();
        int rookCol = (destPosition.getCol() > curPosition.getCol()) ? 7 : 0;
        int rookRow = curPosition.getRow();

        Piece rook = board.getPieceByPosition(new Position(rookCol, rookRow));

        if (isNull(rook) || rook.isMoved() || rook.getColor() != getColor()) {
            return false;
        }

        int minCol = Math.min(curPosition.getCol(), rookCol);
        int maxCol = Math.max(curPosition.getCol(), rookCol);

        for (int i = minCol + 1; i < maxCol; i++) {
            if (board.pieceExistsAt(new Position(curPosition.getRow(), i))) {
                return false;
            }
        }

        for (int col = curPosition.getCol(); col != destPosition.getCol();
             col += Integer.compare(destPosition.getCol(), curPosition.getCol())) {
            if (board.isSquareUnderAttack(new Position(destPosition.getRow(), col), this.getColor())) {
                return false;
            }
        }

        List<Piece> pieces = getBoard().getPieces();

        setRookId(pieces.indexOf(rook));

        return true;
    }

    private static synchronized void setRookId(int id) {
        rookId = id;
    }
}
