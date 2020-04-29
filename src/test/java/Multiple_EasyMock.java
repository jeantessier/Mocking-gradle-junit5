import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;

public class Multiple_EasyMock extends TestCase {
    private IMocksControl control;

    protected void setUp() throws Exception {
        super.setUp();

        control = EasyMock.createControl();
    }

    protected void runTest() throws Throwable {
        super.runTest();
        control.verify();
    }

    public void testSome() {
        SimpleInterface mockSome = control.createMock(SimpleInterface.class);
        SomeOtherInterface mockSomeOther = control.createMock(SomeOtherInterface.class);
        // Program the mocks here
        control.replay();
        
        // Setup SUT with mocks and exercise here
    }
}
