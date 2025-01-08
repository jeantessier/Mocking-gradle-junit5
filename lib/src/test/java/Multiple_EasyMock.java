import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class Multiple_EasyMock {
    private IMocksControl control = EasyMock.createControl();

    @AfterEach
    void runTest() {
        control.verify();
    }

    @Test
    void testSome() {
        SimpleInterface mockSome = control.createMock(SimpleInterface.class);
        SomeOtherInterface mockSomeOther = control.createMock(SomeOtherInterface.class);
        // Program the mocks here
        control.replay();
        
        // Setup SUT with mocks and exercise here
    }
}
