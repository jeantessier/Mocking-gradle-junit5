import junit.framework.TestCase;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class MultipleCalls_EasyMock extends TestCase {
    public void testMultipleCalls() {
        int expectedValue = 42;

        SimpleInterface mockSimple = createMock(SimpleInterface.class);
        expect(mockSimple.simpleMethod()).andReturn(expectedValue).times(2);
        replay(mockSimple);

        Client sut = new Client(mockSimple);
        int actualValue = sut.callSimpleTwice();
        assertEquals(expectedValue, actualValue);

        verify(mockSimple);
    }
}
