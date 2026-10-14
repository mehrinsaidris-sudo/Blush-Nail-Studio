import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class ArtistSchedule {
    private final Map<String, Set<String>> reservedSlots = new HashMap<>();
    private final Set<String> allowedTimes = new HashSet<>(
            Arrays.asList("10:00", "12:00", "14:00", "16:00"));

    public ArtistSchedule() {
        reservedSlots.put("Anna", new HashSet<String>());
        reservedSlots.put("Mia", new HashSet<String>());
    }

    public boolean isAvailable(String artist, String time) {
        validateSlot(artist, time);
        return !reservedSlots.get(artist).contains(time);
    }

    public void reserve(String artist, String time) {
        if (!isAvailable(artist, time)) {
            throw new IllegalStateException("This time is already booked for " + artist + ".");
        }
        reservedSlots.get(artist).add(time);
    }

    public void release(String artist, String time) {
        validateSlot(artist, time);
        if (!reservedSlots.get(artist).remove(time)) {
            throw new IllegalStateException("The time slot was not reserved.");
        }
    }

    private void validateSlot(String artist, String time) {
        if (!reservedSlots.containsKey(artist)) {
            throw new IllegalArgumentException("Unknown artist. Choose Anna or Mia.");
        }
        if (!allowedTimes.contains(time)) {
            throw new IllegalArgumentException("Choose 10:00, 12:00, 14:00 or 16:00.");
        }
    }
}
