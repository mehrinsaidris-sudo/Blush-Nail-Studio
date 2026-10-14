public class NailStudioFacade {
    private final ServiceCatalog catalog;
    private final ArtistSchedule schedule;
    private final PriceCalculator calculator;
    private final BookingManager bookings;
    private final ConfirmationService confirmations;

    public NailStudioFacade() {
        this.catalog = new ServiceCatalog();
        this.schedule = new ArtistSchedule();
        this.calculator = new PriceCalculator();
        this.bookings = new BookingManager();
        this.confirmations = new ConfirmationService();
    }

    public String showServices() {
        StringBuilder menu = new StringBuilder("\nAvailable nail services:\n");
        for (NailService service : catalog.getServices()) {
            menu.append("- ").append(service.getCode()).append(" | ")
                    .append(service.getName()).append(" | ")
                    .append(service.getBasePrice()).append(" KZT\n");
        }
        menu.append("Extra nail art: ").append(calculator.getNailArtPrice()).append(" KZT\n");
        return menu.toString();
    }

    public String bookAppointment(String customer, String serviceCode, String artist,
                                  String time, boolean addNailArt) {
        if (customer == null || customer.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }

        NailService service = catalog.findService(serviceCode);
        if (!schedule.isAvailable(artist, time)) {
            throw new IllegalStateException("This time is already booked for " + artist + ".");
        }
        int total = calculator.calculateTotal(service, addNailArt);

        schedule.reserve(artist, time);
        Appointment appointment = null;
        try {
            appointment = bookings.createBooking(customer, service, artist, time, addNailArt, total);
            return confirmations.bookingConfirmation(appointment);
        } catch (RuntimeException error) {
            if (appointment != null) {
                bookings.cancelBooking(appointment.getId());
            }
            schedule.release(artist, time);
            throw error;
        }
    }

    public String cancelAppointment(String bookingId) {
        Appointment appointment = bookings.getBooking(bookingId);
        schedule.release(appointment.getArtist(), appointment.getTime());
        bookings.cancelBooking(bookingId);
        return confirmations.cancellationConfirmation(appointment);
    }
}
