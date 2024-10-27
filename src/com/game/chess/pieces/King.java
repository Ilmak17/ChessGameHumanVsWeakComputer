package com.game.chess.pieces;

import com.game.chess.board.Board;
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
        Position curPosition = getPosition();
        int dCol = Math.abs(destPosition.getCol() - curPosition.getCol());
        int dRow = Math.abs(destPosition.getRow() - curPosition.getRow());

        if (dCol == 2 && dRow == 0) {
            return canDoCastling(destPosition);
        }

        return dCol <= 1 && dRow <= 1 && isDestinationAvailable(destPosition);
    }

    @Override
    public void move(Position destPosition) {
        if (canDoCastling(destPosition) && isValidMove(destPosition)) {
            performCastling(destPosition);
            return;
        }
        super.move(destPosition);
    }

    private void performCastling(Position destPosition) {
        int kingNewCol = destPosition.getCol() > getPosition().getCol() ? 6 : 2;
        int rookNewCol = destPosition.getCol() > getPosition().getCol() ? 5 : 3;
        List<Piece> pieces = getBoard().getPieces();

        forceMove(new Position(getPosition().getRow(), kingNewCol));
        pieces.get(rookId).forceMove(new Position(getPosition().getRow(), rookNewCol));

        setMoved(true);
        pieces.get(rookId).setMoved(true);
    }

    @Override
    public String getPieceType() {
        return PieceType.KING.getName();
    }

    @Override
    public String getSymbol() {
        return getColor() == Color.BLACK ? "♚" : "♔";
    }

    private boolean canDoCastling(Position destPosition) {
        Board board = getBoard();

        if (board.isKingInCheck(getColor())) {
            return false;
        }

        Position curPosition = getPosition();
        int rookCol = destPosition.getCol() > curPosition.getCol() ? 7 : 0;
        int rookRow = curPosition.getRow();

        Piece rook = board.getPieceByPosition(new Position(rookRow, rookCol));
        if (isNull(rook) || !(rook instanceof Rook) || rook.hasMoved() || rook.getColor() != getColor()) {
            return false;
        }

        if (!isPathClear(curPosition, rookCol)) return false;

        return !isPathUnderAttack(curPosition, destPosition, rook);
    }

    private boolean isPathClear(Position curPosition, int rookCol) {
        int minCol = Math.min(curPosition.getCol(), rookCol);
        int maxCol = Math.max(curPosition.getCol(), rookCol);
        int row = curPosition.getRow();
        for (int col = minCol + 1; col < maxCol; col++) {
            if (getBoard().pieceExistsAt(new Position(row, col))) {
                return false;
            }
        }
        return true;
    }

    private boolean isPathUnderAttack(Position curPosition, Position destPosition, Piece rook) {
        int row = curPosition.getRow();
        for (int col = curPosition.getCol(); col != destPosition.getCol();
             col += Integer.compare(destPosition.getCol(), curPosition.getCol())) {
            if (getBoard().isSquareUnderAttack(new Position(row, col), getColor())) {
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
