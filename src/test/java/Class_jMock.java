import org.jmock.imposters.ByteBuddyClassImposteriser;

public class Class_jMock extends MockObjectTestCase {
    protected void setUp() throws Exception {
        super.setUp();

        setImposteriser(ByteBuddyClassImposteriser.INSTANCE);
    }

    public void testSome() {
        SomeClass mockSome = mock(SomeClass.class);
        // Program the mocks here

        // Setup SUT with mocks and exercise here
    }
}
