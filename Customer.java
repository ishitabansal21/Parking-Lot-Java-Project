package Java_Project;

public class Customer {
    private final String name;
    private final Vehicle vehicle;

    public Customer(String name, String vehicleNumber) {
        this.name = name;
        this.vehicle = new Vehicle(vehicleNumber);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
