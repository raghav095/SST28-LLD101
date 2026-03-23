import java.util.HashMap;
import java.util.Map;

 class Board {
    private int totalCells;
    private Map<Integer, Integer> jumps;

    public Board(int size) {
        this.totalCells = size * size;
        this.jumps = new HashMap<>();
    }


    public void addJump(int start, int end) {
        jumps.put(start, end);
    }

    public int getFinalPosition(int position) {
        if (jumps.containsKey(position)) {
            return jumps.get(position);
        }
        return position;
    }

    public int getTotalCells() {
        return totalCells;
    }
}