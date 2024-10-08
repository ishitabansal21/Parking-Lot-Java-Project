package Java_Project;

import java.time.LocalDateTime;

public class Ticket {
    private static int idCounter = 0;
    private final int id;
    private final String vehicleRegistrationNumber;
    private final LocalDateTime arrivalTime;
    private final String parkingSpotNumber;

    public int getId() {
        return id;
    }

    public Ticket(Vehicle vehicle, ParkingSpot availableSpot) {
        this.id = ++idCounter;
        this.vehicleRegistrationNumber = vehicle.getVehicleNumber();
        this.parkingSpotNumber = availableSpot.getSpotNumber();
        this.arrivalTime = LocalDateTime.now();
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", vehicleRegistrationNumber='" + vehicleRegistrationNumber + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", parkingSpotNumber='" + parkingSpotNumber + '\'' +
                '}';
    }
}
