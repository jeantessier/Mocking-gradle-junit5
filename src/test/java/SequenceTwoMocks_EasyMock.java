import junit.framework.TestCase;
import org.easymock.IMocksControl;

import static org.easymock.EasyMock.createStrictControl;
import static org.easymock.EasyMock.expect;

public class SequenceTwoMocks_EasyMock extends TestCase {
    public void testSequence() {
        int somedValue = 42;

        IMocksControl control = createStrictControl();
        SimpleInterface mockSimple = control.createMock(SimpleInterface.class);
        SomeOtherInterface mockSomeOther = control.createMock(SomeOtherInterface.class);
        mockSomeOther.someOtherMethod();
        expect(mockSimple.simpleMethod()).andReturn(somedValue);
        control.replay();

        Client sut = new Client(mockSimple, mockSomeOther);
        sut.callSimpleAndSomeOther();

        control.verify();
    }
}
