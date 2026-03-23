import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BoardGenerator {

    public static Board createBoard(int size) {
        Board board = new Board(size);

        int totalCells = board.getTotalCells();

        int numberOfSnakes = size;   
        int numberOfLadders = size;

        Random random = new Random();
        Set<Integer> usedStarts = new HashSet<>();

      
        int laddersAdded = 0;
        while (laddersAdded < numberOfLadders) {
            int start = random.nextInt(totalCells - 1) + 2; 
            int end = random.nextInt(totalCells - start) + start + 1;

            if (start >= end) continue;
            if (usedStarts.contains(start)) continue;

            board.addJump(start, end);
            usedStarts.add(start);
            laddersAdded++;
        }

       
        int snakesAdded = 0;
        while (snakesAdded < numberOfSnakes) {
            int start = random.nextInt(totalCells - 1) + 2; 
            int end = random.nextInt(start - 1) + 1;

            if (start <= end) continue;
            if (usedStarts.contains(start)) continue;

            board.addJump(start, end);
            usedStarts.add(start);
            snakesAdded++;
        }

        return board;
    }
}