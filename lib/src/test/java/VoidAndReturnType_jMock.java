import org.jmock.Expectations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class VoidAndReturnType_jMock extends MockObjectTestCase {
    @Test
    void testVoidAndReturnType() {
        final VoidAndReturnType mockVoidAndReturnType = mock(VoidAndReturnType.class);

        checking(new Expectations() {{
            oneOf (mockVoidAndReturnType).methodWithVoid();
            oneOf (mockVoidAndReturnType).methodWithReturnType();
        }});

        VoidAndReturnTypeClient sut = new VoidAndReturnTypeClient(mockVoidAndReturnType);
        sut.callVoidAndReturnType();
    }

    @Test
    void testVoidThrows() {
        final VoidAndReturnType mockVoidAndReturnType = mock(VoidAndReturnType.class);

        checking(new Expectations() {{
            oneOf (mockVoidAndReturnType).methodWithVoid();
            will(throwException(new RuntimeException()));
        }});

        VoidAndReturnTypeClient sut = new VoidAndReturnTypeClient(mockVoidAndReturnType);

        try {
            sut.callVoid();
            fail();
        } catch (RuntimeException e) {
            // Expected
        }
    }

    @Test
    void testReturnTypeThrows() {
        final VoidAndReturnType mockVoidAndReturnType = mock(VoidAndReturnType.class);

        checking(new Expectations() {{
            oneOf (mockVoidAndReturnType).methodWithReturnType();
            will(throwException(new RuntimeException()));
        }});

        VoidAndReturnTypeClient sut = new VoidAndReturnTypeClient(mockVoidAndReturnType);

        try {
            sut.callReturnType();
            fail();
        } catch (RuntimeException e) {
            // Expected
        }
    }
}
