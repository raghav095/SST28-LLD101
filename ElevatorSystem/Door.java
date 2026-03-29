public class Door {
    private boolean isOpen = false;

    public void open() { 
        isOpen = true; 
        System.out.println("Door Opening.");
    }

    public void close(double currentWeight, double maxWeight) {
        if (currentWeight > maxWeight) {
            playAlarm();
        } else {
            isOpen = false;
            System.out.println("Door Closing.");
        }
    }

    private void playAlarm() {
        System.out.println("ALARM! Weight limit of 750kgs breached! Please exit.");
    }

    public boolean isOpen() {
        return isOpen;
    }
}
