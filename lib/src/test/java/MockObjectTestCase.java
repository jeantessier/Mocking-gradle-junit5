import org.jmock.Sequence;
import org.jmock.api.Imposteriser;
import org.jmock.internal.ExpectationBuilder;
import org.jmock.junit5.JUnit5Mockery;
import org.junit.jupiter.api.extension.RegisterExtension;

public abstract class MockObjectTestCase {
    @RegisterExtension
    JUnit5Mockery context = new JUnit5Mockery();

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
