import java.util.LinkedHashMap;
import java.util.Map;
public class BookingManager {
    private final Map<String, Appointment> appointments = new LinkedHashMap<>();
    private int nextBookingNumber = 1;

    public Appointment createBooking(String customer, NailService service, String artist,
                                     String time, boolean nailArt, int totalPrice) {
        if (customer == null || customer.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }
        String bookingId = String.format("NS-%03d", nextBookingNumber++);
        Appointment appointment = new Appointment(
                bookingId, customer.trim(), service, artist, time, nailArt, totalPrice);
        appointments.put(bookingId, appointment);
        return appointment;
    }

    public Appointment getBooking(String bookingId) {
        Appointment appointment = appointments.get(bookingId);
        if (appointment == null) {
            throw new IllegalArgumentException("Booking not found: " + bookingId);
        }
        return appointment;
    }

    public void cancelBooking(String bookingId) {
        getBooking(bookingId);
        appointments.remove(bookingId);
    }
}
