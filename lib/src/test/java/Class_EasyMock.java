import junit.framework.TestCase;
import org.junit.Ignore;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

@Ignore("EasyMock doesn't mock classes")
public class Class_EasyMock extends TestCase {
    public void testSome() {
        SomeClass mockSome = createMock(SomeClass.class);
        // Program the mocks here
        replay(mockSome);

        // Setup SUT with mocks and exercise here

        verify(mockSome);
    }
}
