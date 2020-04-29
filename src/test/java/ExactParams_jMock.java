import org.jmock.Expectations;

public class ExactParams_jMock extends MockObjectTestCase {
    public void testTwoParams() {
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
