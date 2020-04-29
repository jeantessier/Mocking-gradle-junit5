import junit.framework.TestCase;
import org.jmock.Sequence;
import org.jmock.api.Imposteriser;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.internal.ExpectationBuilder;
import org.junit.Rule;

public abstract class MockObjectTestCase extends TestCase {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    protected void runTest() throws Throwable {
        super.runTest();
        context.assertIsSatisfied();
    }

    protected void setImposteriser(Imposteriser imposteriser) {
        context.setImposteriser(imposteriser);
    }

    protected <T> T mock(Class<T> typeToMock) {
        return context.mock(typeToMock);
    }

    protected <T> T mock(Class<T> typeToMock, String name) {
        return context.mock(typeToMock, name);
    }

    protected Sequence sequence(String name) {
        return context.sequence(name);
    }

    protected void checking(ExpectationBuilder expectations) {
        context.checking(expectations);
    }
}
