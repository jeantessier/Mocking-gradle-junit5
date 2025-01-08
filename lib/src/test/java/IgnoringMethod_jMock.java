import org.jmock.Expectations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IgnoringMethod_jMock extends MockObjectTestCase {
    @Test
    void testSimple() {
        final int expectedValue = 42;

        final SimpleInterface mockSimple = mock(SimpleInterface.class);

        checking(new Expectations() {{
            oneOf (mockSimple).simpleMethod();
            will(returnValue(expectedValue));
            ignoring (mockSimple).irrelevantMethod();
            will(returnValue(expectedValue));
        }});

        Client sut = new Client(mockSimple);
        int actualValue = sut.callSimpleAndIrrelevant();
        assertEquals(expectedValue, actualValue);
    }
}
