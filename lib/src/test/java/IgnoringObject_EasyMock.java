import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IgnoringObject_EasyMock {
    @Test
    void testSimple() {
        int expectedValue = 42;

        SimpleInterface mockSimple = createMock(SimpleInterface.class);
        SomeOtherInterface mockSomeOther = createNiceMock(SomeOtherInterface.class);

        expect(mockSimple.simpleMethod()).andReturn(expectedValue);

        replay(mockSimple, mockSomeOther);

        Client sut = new Client(mockSimple, mockSomeOther);
        int actualValue = sut.callSimpleAndSomeOther();
        assertEquals(expectedValue, actualValue);

        verify(mockSimple, mockSomeOther);
    }
}
