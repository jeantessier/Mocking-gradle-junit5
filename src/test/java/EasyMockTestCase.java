import junit.framework.TestCase;
import org.easymock.IMocksControl;
import org.easymock.EasyMock;

public abstract class EasyMockTestCase extends TestCase {
    private IMocksControl control;

    protected void setUp() throws Exception {
        super.setUp();

        control = EasyMock.createControl();
    }

    protected void runTest() throws Throwable {
        super.runTest();
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
