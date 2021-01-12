import junit.framework.TestCase;
import org.jmock.imposters.ByteBuddyClassImposteriser;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;

public class ClassExplicit1_jMock extends TestCase {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery() {{
        setImposteriser(ByteBuddyClassImposteriser.INSTANCE);
    }};

    public void testSome() {
        SomeClass mockSome = context.mock(SomeClass.class);
        // Program the mocks here

        // Setup SUT with mocks and exercise here
    }
}
