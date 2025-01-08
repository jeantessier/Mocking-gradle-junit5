import org.jmock.Expectations;
import org.jmock.Sequence;
import org.junit.jupiter.api.Test;

public class SequenceOneMock_jMock extends MockObjectTestCase {
    @Test
    void testSequence() {
        final SimpleInterface mockSimple = mock(SimpleInterface.class);
        final Sequence simpleSequence = sequence("simple");

        checking(new Expectations() {{
            oneOf (mockSimple).irrelevantMethod();
            inSequence(simpleSequence);
            oneOf (mockSimple).simpleMethod();
            inSequence(simpleSequence);
        }});

        Client sut = new Client(mockSimple);
        sut.callSimpleAndIrrelevant();
    }
}
