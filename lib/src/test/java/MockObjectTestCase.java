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

    protected <T, R> R mock(Class<?> typeToMock) {
        return context.mock((Class<R>) typeToMock);
    }

    protected <T, R> R mock(Class<?> typeToMock, String name) {
        return context.mock((Class<R>) typeToMock, name);
    }

    protected Sequence sequence(String name) {
        return context.sequence(name);
    }

    protected void checking(ExpectationBuilder expectations) {
        context.checking(expectations);
    }
}
