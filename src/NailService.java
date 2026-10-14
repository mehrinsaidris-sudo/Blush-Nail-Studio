public final class NailService {
    private final String code;
    private final String name;
    private final int basePrice;

    public NailService(String code, String name, int basePrice) {
        this.code = code;
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
