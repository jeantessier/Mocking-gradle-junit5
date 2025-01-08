import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class Single_EasyMock {
    @Test
    void testSome() {
        SomeInterface mockSome = createMock(SomeInterface.class);
        // Program the mock here
        replay(mockSome);

        // Setup SUT with mock and exercise here

        verify(mockSome);
    }
}
