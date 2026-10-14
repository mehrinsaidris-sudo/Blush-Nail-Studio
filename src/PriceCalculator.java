public class PriceCalculator {
    private static final int NAIL_ART_PRICE = 2000;

    public int getNailArtPrice() {
        return NAIL_ART_PRICE;
    }

    public int calculateTotal(NailService service, boolean addNailArt) {
        if (service == null) {
            throw new IllegalArgumentException("Service is required to calculate a price.");
        }
        return service.getBasePrice() + (addNailArt ? NAIL_ART_PRICE : 0);
    }
}
