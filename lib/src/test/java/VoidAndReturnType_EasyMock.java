import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.fail;

public class VoidAndReturnType_EasyMock {
    @Test
    void testVoidAndReturnType() {
        VoidAndReturnType mockVoidAndReturnType = createMock(VoidAndReturnType.class);

        mockVoidAndReturnType.methodWithVoid();
        expect(mockVoidAndReturnType.methodWithReturnType()).andReturn(0);

        replay(mockVoidAndReturnType);

        VoidAndReturnTypeClient sut = new VoidAndReturnTypeClient(mockVoidAndReturnType);
        sut.callVoidAndReturnType();

        verify(mockVoidAndReturnType);
    }

    @Test void testVoidThrows() {
        VoidAndReturnType mockVoidAndReturnType = createMock(VoidAndReturnType.class);

        mockVoidAndReturnType.methodWithVoid();
        expectLastCall().andThrow(new RuntimeException());

        replay(mockVoidAndReturnType);

        VoidAndReturnTypeClient sut = new VoidAndReturnTypeClient(mockVoidAndReturnType);
        try {
            sut.callVoid();
            fail();
        } catch (Exception e) {
            // Expected
        }

        verify(mockVoidAndReturnType);
    }

    @Test
    void testReturnTypeThrows() {
        VoidAndReturnType mockVoidAndReturnType = createMock(VoidAndReturnType.class);

        expect(mockVoidAndReturnType.methodWithReturnType()).andThrow(new RuntimeException());

        replay(mockVoidAndReturnType);

        VoidAndReturnTypeClient sut = new VoidAndReturnTypeClient(mockVoidAndReturnType);
        try {
            sut.callReturnType();
            fail();
        } catch (Exception e) {
            // Expected
        }

        verify(mockVoidAndReturnType);
    }
}
