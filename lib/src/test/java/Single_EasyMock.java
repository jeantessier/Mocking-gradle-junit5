import junit.framework.TestCase;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class Single_EasyMock extends TestCase {
    public void testSome() {
        SomeInterface mockSome = createMock(SomeInterface.class);
        // Program the mock here
        replay(mockSome);

        // Setup SUT with mock and exercise here

        verify(mockSome);
    }
}
