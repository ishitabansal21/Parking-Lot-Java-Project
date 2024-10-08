package Java_Project;

public class DisplayBoard {
    private Status status;

    public DisplayBoard() {
        this.status = Status.AVAILABLE;
    }

    public void update(Status newStatus) {
        this.status = newStatus;
    }
}
