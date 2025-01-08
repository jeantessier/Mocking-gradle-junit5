import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

@Disabled("EasyMock doesn't mock classes")
public class Class_EasyMock {
    @Test
    void testSome() {
        SomeClass mockSome = createMock(SomeClass.class);
        // Program the mocks here
        replay(mockSome);

        // Setup SUT with mocks and exercise here

        verify(mockSome);
    }
}
