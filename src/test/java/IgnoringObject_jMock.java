import org.jmock.Expectations;

public class IgnoringObject_jMock extends MockObjectTestCase {
    public void testSimple() {
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
