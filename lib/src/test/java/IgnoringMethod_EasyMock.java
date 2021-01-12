import junit.framework.TestCase;
import static org.easymock.EasyMock.*;

public class IgnoringMethod_EasyMock extends TestCase {
    public void testSimple() {
        int expectedValue = 42;

        SimpleInterface mockSimple = createMock(SimpleInterface.class);
        expect(mockSimple.simpleMethod()).andReturn(expectedValue);
        expect(mockSimple.irrelevantMethod()).andStubReturn(expectedValue);
        replay(mockSimple);

        Client sut = new Client(mockSimple);
        int actualValue = sut.callSimpleAndIrrelevant();
        assertEquals(expectedValue, actualValue);

        verify(mockSimple);
    }
}
