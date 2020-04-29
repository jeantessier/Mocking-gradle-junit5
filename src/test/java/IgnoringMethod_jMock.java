import org.jmock.Expectations;

public class IgnoringMethod_jMock extends MockObjectTestCase {
    public void testSimple() {
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
