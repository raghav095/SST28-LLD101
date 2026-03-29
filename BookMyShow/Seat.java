public class Seat {
    private final int id;
    private final int row;
    private final int column;

    public Seat(int id, int row, int column) {
        this.id = id;
        this.row = row;
        this.column = column;
    }

    public int getId() { return id; }
    public int getRow() { return row; }
    public int getColumn() { return column; }
} 
