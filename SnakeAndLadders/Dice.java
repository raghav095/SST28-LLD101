public class Dice {
  private int faces;
     
 public Dice(int faces) {
        if (faces <= 0) {
            throw new IllegalArgumentException("Faces must be > 0");
        }
        this.faces = faces;
    }

  public int roll(){
    return (int) (Math.random() * faces) + 1;
  }
}


//  dice should have faces 1 to 6
