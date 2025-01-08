import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class SequenceOneMock_EasyMock {
    @Test
    void testSequence() {
        int somedValue = 42;

        SimpleInterface mockSimple = createStrictMock(SimpleInterface.class);
        expect(mockSimple.irrelevantMethod()).andReturn(somedValue);
        expect(mockSimple.simpleMethod()).andReturn(somedValue);
        replay(mockSimple);

        Client sut = new Client(mockSimple);
        sut.callSimpleAndIrrelevant();

        verify(mockSimple);
    }
}
