package Java_Project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLot {
    private final String name;
    private final int capacity;
    private final String location;
    private double parkingCharges;
    private Admin admin;
    private final AutomatedSystem automatedSystem;
    private final ArrayList<ParkingSpot> parkingSpots;
    private final ArrayList<ParkingSpot> availableSpots;
    private final DisplayBoard displayBoard;

    public ParkingLot(String name, int capacity, String location, double parkingCharges, AutomatedSystem automatedSystem, DisplayBoard displayBoard) {
        this.name = name;
        this.capacity = capacity;
        this.location = location;
        this.parkingCharges = parkingCharges;
        this.automatedSystem = automatedSystem;
        this.automatedSystem.setParkingLot(this);
        this.displayBoard = displayBoard;
        this.parkingSpots = createParkingSpots(capacity);
        this.availableSpots = new ArrayList<>(parkingSpots);
    }

    private ArrayList<ParkingSpot> createParkingSpots(int capacity) {
        ArrayList<ParkingSpot> tempList = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            ParkingSpot temp = new ParkingSpot("MPL " + i);
            tempList.add(temp);
        }
        return tempList;
    }

    public DisplayBoard getDisplayBoard() {
        return displayBoard;
    }

    public ParkingSpot getAvailableSpot() {
        return availableSpots.remove(0);
    }

    public int getAvailability() {
        return availableSpots.size();
    }

    public double getParkingCharges() {
        return parkingCharges;
    }

    public void updateParkingCharges(double newCharges) {
        this.parkingCharges = newCharges;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public AutomatedSystem getAutomatedSystem() {
        return automatedSystem;
    }

    public static void main(String[] args) {
        // Create a DisplayBoard
        DisplayBoard displayBoard = new DisplayBoard();

        // Create an AutomatedSystem with an ID
        AutomatedSystem automatedSystem = new AutomatedSystem(1);

        // Create a ParkingLot with a name, capacity, location, parking charges, automated system, and display board
        ParkingLot myParkingLot = new ParkingLot("MyParkingLot", 4, "Sec-135,Noida", 50, automatedSystem, displayBoard);

        // Print a welcome message
        System.out.println("******************************");
        System.out.println("*                            *");
        System.out.println("* WELCOME TO JIIT PARKING LOT *");
        System.out.println("* (Your Vehicle is Safe with Us) *");
        System.out.println("*                            *");
        System.out.println("******************************\n\n\n\n");

        // Print the capacity of the parking lot
        System.out.println("Capacity: " + myParkingLot.getAvailability());

        // Create customers and generate tickets
        Customer vaishali = new Customer("Vaishali", "UP85 AX 5454");
        Ticket vaishaliTicket = myParkingLot.getAutomatedSystem().generateTicket(vaishali);
        System.out.println(vaishaliTicket);

        Customer asmi = new Customer("Asmi", "UP86 AB 9999");
        Ticket asmiTicket = myParkingLot.getAutomatedSystem().generateTicket(asmi);
        System.out.println(asmiTicket);

        Customer tanmay = new Customer("Tanmay", "UP86 DK 6810");
        Ticket tanmayTicket = myParkingLot.getAutomatedSystem().generateTicket(tanmay);
        System.out.println(tanmayTicket);

        Customer pulkit = new Customer("Pulkit", "UP86 ACC 5473");
        Ticket pulkitTicket = myParkingLot.getAutomatedSystem().generateTicket(pulkit);
        System.out.println(pulkitTicket);

        Customer shantanu = new Customer("Shantanu", "UP86 AB 4567");
        Ticket shantanuTicket = myParkingLot.getAutomatedSystem().generateTicket(shantanu);
        System.out.println(shantanuTicket);
    }
}

class AutomatedSystem {
    private final int id;
    private ParkingLot parkingLot;
    private final HashMap<Integer, Ticket> currentTickets;

    public AutomatedSystem(int id) {
        this.id = id;
        this.currentTickets = new HashMap<>();
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    private ParkingSpot fetchAvailableSpot() {
        return this.parkingLot.getAvailableSpot();
    }

    public Ticket generateTicket(Customer customer) {
        ParkingSpot availableSpot = fetchAvailableSpot();
        Vehicle vehicle = customer.getVehicle();
        Ticket ticket = new Ticket(vehicle, availableSpot);
        currentTickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public Ticket scanTicket() {
        // Code for scanning the ticket and return ticketId
        // Here we are assuming that scanned ticket id is 1234
        return currentTickets.get(1234);
    }

    public double calculateCharges() {
        Ticket ticket = scanTicket();
        long duration = Duration.between(LocalDateTime.now(), ticket.getArrivalTime()).toHours();
        return duration * parkingLot.getParkingCharges();
    }

    public void openEntryBarrier() {
        // Code for opening Entry Barrier
        this.parkingLot.getDisplayBoard().update(Status.FULL);
    }

    public void closeEntryBarrier() {
        // Code for closing Entry Barrier
    }

    public void openExitBarrier() {
        // Code for opening Entry Barrier
        this.parkingLot.getDisplayBoard().update(Status.AVAILABLE);
    }

    public void closeExitBarrier() {
        // Code for closing Entry Barrier
    }
}

