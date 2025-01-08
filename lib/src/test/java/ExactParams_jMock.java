import org.jmock.Expectations;
import org.junit.jupiter.api.Test;

public class ExactParams_jMock extends MockObjectTestCase {
    @Test
    void testTwoParams() {
        final int intValue = 42;
        final String textValue = "forty-two";

        final SimpleInterface mockSimple = mock(SimpleInterface.class);

        checking(new Expectations() {{
            oneOf (mockSimple).twoParamMethod(intValue, textValue);
        }});

        Client sut = new Client(mockSimple);
        sut.callTwoParams(intValue, textValue);
    }
}
