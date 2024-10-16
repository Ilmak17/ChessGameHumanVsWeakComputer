public class Position {
    private int row;
    private int col;

    private Position(Builder builder) {
        setRow(builder.row);
        setCol(builder.col);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public static final class Builder {
        private int row;
        private int col;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder row(int val) {
            row = val;
            return this;
        }

        public Builder col(int val) {
            col = val;
            return this;
        }

        public Position build() {
            return new Position(this);
        }
    }
}
