import org.jmock.Expectations;

public class MultipleCalls_jMock extends MockObjectTestCase {
    public void testMultipleCalls() {
        final int expectedValue = 42;

        final SimpleInterface mockSimple = mock(SimpleInterface.class);

        checking(new Expectations() {{
            exactly(2).of (mockSimple).simpleMethod();
            will(returnValue(expectedValue));
        }});

        Client sut = new Client(mockSimple);
        int actualValue = sut.callSimpleTwice();
        assertEquals(expectedValue, actualValue);
    }
}
