import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        NailStudioFacade studio = new NailStudioFacade();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Blush Nail Studio!");
        boolean running = true;
        while (running) {
            System.out.println("\n1. View services");
            System.out.println("2. Book an appointment");
            System.out.println("3. Cancel an appointment");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().trim();

            try {
                switch (option) {
                    case "1":
                        System.out.println(studio.showServices());
                        break;
                    case "2":
                        System.out.print("Your name: ");
                        String customer = scanner.nextLine();
                        System.out.println(studio.showServices());
                        System.out.print("Service code (classic/gel/spa): ");
                        String service = scanner.nextLine();
                        System.out.print("Artist (Anna/Mia): ");
                        String artist = scanner.nextLine();
                        System.out.print("Time (10:00/12:00/14:00/16:00): ");
                        String time = scanner.nextLine();
                        System.out.print("Add nail art? (yes/no): ");
                        boolean nailArt = scanner.nextLine().trim().equalsIgnoreCase("yes");
                        System.out.println(studio.bookAppointment(customer, service, artist, time, nailArt));
                        break;
                    case "3":
                        System.out.print("Booking ID (example: NS-001): ");
                        System.out.println(studio.cancelAppointment(scanner.nextLine().trim()));
                        break;
                    case "0":
                        running = false;
                        System.out.println("Thank you for visiting Blush Nail Studio!");
                        break;
                    default:
                        System.out.println("Please choose 0, 1, 2 or 3.");
                }
            } catch (IllegalArgumentException | IllegalStateException error) {
                System.out.println("Sorry, booking could not be completed: " + error.getMessage());
            }
        }
    }
}
