import exception.ChessException;
import pieces.Piece;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
    }

    private Board(Builder builder) {
        if (builder.rows < 1 || builder.columns < 1) {
            // todo add exception message
            throw new ChessException("");
        }
        setRows(builder.rows);
        setColumns(builder.columns);
        setPieces(builder.pieces);
    }


    // todo add custom lombok
    public static final class Builder {
        private int rows;
        private int columns;
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

        public Builder columns(int val) {
            columns = val;
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
