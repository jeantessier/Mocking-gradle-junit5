import org.junit.jupiter.api.Test;

public class Inherited_EasyMock extends EasyMockTestCase {
    @Test
    void testSome() {
        SomeInterface mockSome = createMock(SomeInterface.class);
        // Program the mocks here
        replay();

        // Setup SUT with mocks and exercise here
    }
}
