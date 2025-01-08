import org.jmock.junit5.JUnit5Mockery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class Multiple_jMock {
    @RegisterExtension
    JUnit5Mockery context = new JUnit5Mockery();

    @Test
    void testSome() {
        SomeInterface mockSome = context.mock(SomeInterface.class);
        SomeOtherInterface mockSomeOther = context.mock(SomeOtherInterface.class);
        // Program the mocks here

        // Setup SUT with mocks and exercise here
    }
}
