import org.jmock.Expectations;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;

public class FuzzyParams_jMock extends MockObjectTestCase {
    @Test
    void testTwoParams() {
        final int intValue = 42;
        final String textValue = "forty-two";

        final SimpleInterface mockSimple = mock(SimpleInterface.class);

        checking(new Expectations() {{
            oneOf (mockSimple).twoParamMethod(with(greaterThan(40)), with(containsString("two")));
        }});

        Client sut = new Client(mockSimple);
        sut.callTwoParams(intValue, textValue);
    }
}
