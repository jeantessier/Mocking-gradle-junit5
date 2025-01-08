import org.jmock.Expectations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IgnoringObject_jMock extends MockObjectTestCase {
    @Test
    void testSimple() {
        final int expectedValue = 42;

        final SimpleInterface mockSimple = mock(SimpleInterface.class);
        final SomeOtherInterface mockSomeOther = mock(SomeOtherInterface.class);

        checking(new Expectations() {{
            oneOf (mockSimple).simpleMethod();
            will(returnValue(expectedValue));
            ignoring (mockSomeOther);
        }});

        Client sut = new Client(mockSimple, mockSomeOther);
        int actualValue = sut.callSimpleAndSomeOther();
        assertEquals(expectedValue, actualValue);
    }
}
