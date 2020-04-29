public class Inherited_EasyMock extends EasyMockTestCase {
    public void testSome() {
        SomeInterface mockSome = createMock(SomeInterface.class);
        // Program the mocks here
        replay();

        // Setup SUT with mocks and exercise here
    }
}
