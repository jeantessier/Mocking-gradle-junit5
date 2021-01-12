import junit.framework.TestCase;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;

public class Multiple_jMock extends TestCase {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    protected void runTest() throws Throwable {
        super.runTest();
        context.assertIsSatisfied();
    }
    
    public void testSome() {
        SomeInterface mockSome = context.mock(SomeInterface.class);
        SomeOtherInterface mockSomeOther = context.mock(SomeOtherInterface.class);
        // Program the mocks here

        // Setup SUT with mocks and exercise here
    }
}
