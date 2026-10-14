/** Simple dependency-free automated checks. Run: java -cp out NailStudioTest */
public class NailStudioTest {
    private static int passed = 0;

    public static void main(String[] args) {
        NailStudioFacade studio = new NailStudioFacade();

        String result = studio.bookAppointment("Aigerim", "gel", "Anna", "14:00", true);
        check(result.contains("Booking ID: NS-001"), "booking creates an ID");
        check(result.contains("Total: 10000 KZT"), "nail art is included in price");
        expectError(() -> studio.bookAppointment("Dana", "classic", "Anna", "14:00", false),
                IllegalStateException.class, "cannot double-book one artist");
        check(studio.bookAppointment("Dana", "classic", "Mia", "14:00", false)
                .contains("Booking ID: NS-002"), "another artist is available at the same time");
        expectError(() -> studio.bookAppointment("Dana", "unknown", "Anna", "10:00", false),
                IllegalArgumentException.class, "rejects unknown services");
        expectError(() -> studio.bookAppointment("Dana", "gel", "Kate", "10:00", false),
                IllegalArgumentException.class, "rejects unknown artists");
        expectError(() -> studio.bookAppointment("", "gel", "Anna", "10:00", false),
                IllegalArgumentException.class, "rejects missing customer names");
        check(studio.cancelAppointment("NS-001").contains("canceled"), "cancels a booking");
        check(studio.bookAppointment("Aigerim", "spa", "Anna", "14:00", false)
                .contains("Booking ID: NS-003"), "cancellation frees the time slot");
        expectError(() -> studio.cancelAppointment("NS-999"),
                IllegalArgumentException.class, "rejects unknown booking IDs");

        System.out.println("All " + passed + " checks passed.");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new AssertionError("FAILED: " + description);
        }
        passed++;
        System.out.println("PASS: " + description);
    }

    private static void expectError(Runnable action, Class<? extends RuntimeException> errorClass,
                                    String description) {
        try {
            action.run();
        } catch (RuntimeException error) {
            check(errorClass.isInstance(error), description);
            return;
        }
        throw new AssertionError("FAILED (no error): " + description);
    }
}
