import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IgnoringMethod_EasyMock {
    @Test
    void testSimple() {
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
