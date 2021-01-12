import java.util.Map;
import java.util.logging.Logger;

public class Cache {
    private Map<Integer, String> underlyingStorage;
    private Logger logger;

    public Cache(Map<Integer, String> underlyingStorage) {
        this(underlyingStorage, null);
    }

    public Cache(Map<Integer, String> underlyingStorage, Logger logger) {
        this.underlyingStorage = underlyingStorage;
        this.logger = logger;
    }

    public void setUnderlyingStorage(Map<Integer, String> underlyingStorage) {
        this.underlyingStorage = underlyingStorage;
    }

    public String get(int key) {
        return underlyingStorage.get(key);
    }

    public void add(int key, String value) {
        underlyingStorage.put(key, value);
    }

    public void remove(int key) {
        underlyingStorage.remove(key);
    }

    public int size() {
        return underlyingStorage.size();
    }

    public void clear() {
        underlyingStorage.clear();
    }

    public void logAndClear() {
        log("Clearing cache that had " + size() + " entries.");
        underlyingStorage.clear();
    }

    public void conditionalLogAndClear() {
        if (size() > 0) {
            logAndClear();
        }
    }

    public void log(String message) {
        if (logger != null) {
            logger.info(message);
        }
    }

    public String getAndLog(int key) {
        String result = get(key);
        log(key + " --> \"" + result + "\"");
        return result;
    }
}
