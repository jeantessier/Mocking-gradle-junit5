import junit.framework.TestCase;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class Innocuous_EasyMock extends TestCase {
    public void testSimple() {
        int expectedValue = 0;

        SimpleInterface mockSimple = createNiceMock(SimpleInterface.class);
        replay(mockSimple);

        Client sut = new Client(mockSimple);
        int actualValue = sut.callSimple();
        assertEquals(expectedValue, actualValue);

        verify(mockSimple);
    }
}
