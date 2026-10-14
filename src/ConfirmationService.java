public class ConfirmationService {
    public String bookingConfirmation(Appointment booking) {
        return "\n--- BLUSH NAIL STUDIO ---\n"
                + "Appointment confirmed!\n"
                + "Booking ID: " + booking.getId() + "\n"
                + "Customer: " + booking.getCustomer() + "\n"
                + "Service: " + booking.getService().getName() + "\n"
                + "Nail artist: " + booking.getArtist() + "\n"
                + "Time: " + booking.getTime() + "\n"
                + "Nail art: " + (booking.hasNailArt() ? "Yes" : "No") + "\n"
                + "Total: " + booking.getTotalPrice() + " KZT\n";
    }

    public String cancellationConfirmation(Appointment booking) {
        return "Booking " + booking.getId() + " canceled. "
                + booking.getArtist() + " is available at " + booking.getTime() + " again.";
    }
}
