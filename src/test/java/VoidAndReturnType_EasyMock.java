import junit.framework.TestCase;
import static org.easymock.EasyMock.*;

public class VoidAndReturnType_EasyMock extends TestCase {
    public void testVoidAndReturnType() {
        VoidAndReturnType mockVoidAndReturnType = createMock(VoidAndReturnType.class);

        mockVoidAndReturnType.methodWithVoid();
        expect(mockVoidAndReturnType.methodWithReturnType()).andReturn(0);

        replay(mockVoidAndReturnType);

        VoidAndReturnTypeClient sut = new VoidAndReturnTypeClient(mockVoidAndReturnType);
        sut.callVoidAndReturnType();

        verify(mockVoidAndReturnType);
    }

    public void testVoidThrows() {
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

    public void testReturnTypeThrows() {
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