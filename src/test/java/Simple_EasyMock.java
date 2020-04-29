import junit.framework.TestCase;
import static org.easymock.EasyMock.*;

public class Simple_EasyMock extends TestCase {
    public void testSimple() {
        int expectedValue = 42;

        SimpleInterface mockSimple = createMock(SimpleInterface.class);
        expect(mockSimple.simpleMethod()).andReturn(expectedValue);
        replay(mockSimple);

        Client sut = new Client(mockSimple);
        int actualValue = sut.callSimple();
        assertEquals(expectedValue, actualValue);

        verify(mockSimple);
    }
}
