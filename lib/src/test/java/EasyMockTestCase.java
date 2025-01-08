import org.easymock.IMocksControl;
import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterEach;

public abstract class EasyMockTestCase {
    private final IMocksControl control = EasyMock.createControl();

    @AfterEach
    void runTest() {
        control.verify();
    }

    protected <T> T createMock(Class<T> clazz) {
        return control.createMock(clazz);
    }

    protected <T> T createMock(String name, Class<T> clazz) {
        return control.createMock(name, clazz);
    }

    protected void replay() {
        control.replay();
    }
}
