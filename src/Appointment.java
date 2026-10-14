public final class Appointment {
    private final String id;
    private final String customer;
    private final NailService service;
    private final String artist;
    private final String time;
    private final boolean nailArt;
    private final int totalPrice;

    public Appointment(String id, String customer, NailService service, String artist,
                       String time, boolean nailArt, int totalPrice) {
        this.id = id;
        this.customer = customer;
        this.service = service;
        this.artist = artist;
        this.time = time;
        this.nailArt = nailArt;
        this.totalPrice = totalPrice;
    }

    public String getId() { return id; }
    public String getCustomer() { return customer; }
    public NailService getService() { return service; }
    public String getArtist() { return artist; }
    public String getTime() { return time; }
    public boolean hasNailArt() { return nailArt; }
    public int getTotalPrice() { return totalPrice; }
}
