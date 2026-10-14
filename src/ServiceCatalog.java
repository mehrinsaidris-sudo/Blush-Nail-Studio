import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
public class ServiceCatalog {
    private final Map<String, NailService> services = new LinkedHashMap<>();

    public ServiceCatalog() {
        register(new NailService("classic", "Classic Manicure", 5000));
        register(new NailService("gel", "Gel Manicure", 8000));
        register(new NailService("spa", "Spa Manicure", 12000));
    }

    private void register(NailService service) {
        services.put(service.getCode(), service);
    }

    public NailService findService(String serviceCode) {
        if (serviceCode == null) {
            throw new IllegalArgumentException("Service code cannot be empty.");
        }
        NailService service = services.get(serviceCode.trim().toLowerCase(Locale.ROOT));
        if (service == null) {
            throw new IllegalArgumentException("Unknown service. Choose classic, gel or spa.");
        }
        return service;
    }

    public Collection<NailService> getServices() {
        return Collections.unmodifiableCollection(services.values());
    }
}
