import org.jmock.Expectations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Innocuous_jMock extends MockObjectTestCase {
    @Test
    void testSimple() {
        final int expectedValue = 0;

        final SimpleInterface mockSimple = mock(SimpleInterface.class);

        checking(new Expectations() {{
            oneOf (mockSimple).simpleMethod();
        }});

        Client sut = new Client(mockSimple);
        int actualValue = sut.callSimple();
        assertEquals(expectedValue, actualValue);
    }
}
