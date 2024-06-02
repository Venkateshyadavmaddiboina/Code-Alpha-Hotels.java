import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private int number;
    private boolean reserved;
    private String guestName;

    public Room(int number) {
        this.number = number;
        this.reserved = false;
        this.guestName = "";
    }

    public int getNumber() {
        return number;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void reserve(String guestName) {
        this.reserved = true;
        this.guestName = guestName;
    }

    public void cancelReservation() {
        this.reserved = false;
        this.guestName = "";
    }

    public String getGuestName() {
        return guestName;
    }
}

class Hotel {
    private List<Room> rooms;

    public Hotel(int numberOfRooms) {
        rooms = new ArrayList<>();
        for (int i = 1; i <= numberOfRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (!room.isReserved()) {
                System.out.println("Room " + room.getNumber());
            }
        }
    }

    public boolean reserveRoom(int roomNumber, String guestName) {
        for (Room room : rooms) {
            if (room.getNumber() == roomNumber && !room.isReserved()) {
                room.reserve(guestName);
                return true;
            }
        }
        return false;
    }

    public boolean cancelReservation(int roomNumber) {
        for (Room room : rooms) {
            if (room.getNumber() == roomNumber && room.isReserved()) {
                room.cancelReservation();
                return true;
            }
        }
        return false;
    }
}

public class Hotels{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel(10); // Creating a hotel with 10 rooms

        while (true) {
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Reserve a Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.nextLine();
                    if (hotel.reserveRoom(roomNumber, guestName)) {
                        System.out.println("Room reserved successfully.");
                    } else {
                        System.out.println("Room is not available or invalid room number.");
                    }
                    break;
                case 3:
                    System.out.print("Enter room number to cancel reservation: ");
                    int roomToCancel = scanner.nextInt();
                    if (hotel.cancelReservation(roomToCancel)) {
                        System.out.println("Reservation canceled successfully.");
                    } else {
                        System.out.println("Room is not reserved or invalid room number.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}

