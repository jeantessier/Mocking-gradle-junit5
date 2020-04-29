import org.jmock.Expectations;
import org.jmock.Sequence;

public class SequenceTwoMocks_jMock extends MockObjectTestCase {
    public void testSequence() {
        final SimpleInterface mockSimple = mock(SimpleInterface.class);
        final SomeOtherInterface mockSomeOther = mock(SomeOtherInterface.class);
        final Sequence crossSequence = sequence("cross");

        checking(new Expectations() {{
            oneOf (mockSomeOther).someOtherMethod();
            inSequence(crossSequence);
            oneOf (mockSimple).simpleMethod();
            inSequence(crossSequence);
        }});

        Client sut = new Client(mockSimple, mockSomeOther);
        sut.callSimpleAndSomeOther();
    }
}
