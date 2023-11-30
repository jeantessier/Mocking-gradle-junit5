import junit.framework.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class Single_Mockito extends TestCase {
    public void testSome() {
        SomeInterface mockSome = mock(SomeInterface.class);
        // Provide stub behavior for the mock here

        // Setup SUT with mock and exercise here

        // Verify calls to the mock after the fact
        // verify(mockSome).someMethod();
    }
}
