public class UserCache {
    private Storage underlyingStorage;
    private Logger logger;

    public UserCache(Storage underlyingStorage, Logger logger) {
        this.underlyingStorage = underlyingStorage;
        this.logger = logger;
    }

    public UserRecord getAndLog(int key) {
        UserRecord result = underlyingStorage.get(key);
        logger.log(key + " --> \"" + result + "\"");
        return result;
    }

    public UserRecord getAndLogName(int key) {
        UserRecord result = underlyingStorage.get(key);
        logger.log(key + " --> \"" + result.getLastName() + ", " + result.getFirstName() + "\"");
        return result;
    }
}
