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
        Set<Integer> usedCells = new HashSet<>();
        int maxAttempts = 10000;

      
        int laddersAdded = 0;
        int attempts = 0;
        while (laddersAdded < numberOfLadders && attempts < maxAttempts) {
            attempts++;
            int start = random.nextInt(totalCells - 2) + 2; 
            int end = random.nextInt(totalCells - start) + start + 1;

            if (start >= end) continue;
            if (usedCells.contains(start) || usedCells.contains(end)) continue;

            board.addJump(start, end);
            usedCells.add(start);
            usedCells.add(end);
            laddersAdded++;
        }

       
        int snakesAdded = 0;
        attempts = 0;
        while (snakesAdded < numberOfSnakes && attempts < maxAttempts) {
            attempts++;
            int start = random.nextInt(totalCells - 2) + 2; 
            int end = random.nextInt(start - 1) + 1;

            if (start <= end) continue;
            if (usedCells.contains(start) || usedCells.contains(end)) continue;

            board.addJump(start, end);
            usedCells.add(start);
            usedCells.add(end);
            snakesAdded++;
        }

        return board;
    }
}