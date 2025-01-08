import org.jmock.Expectations;
import org.jmock.Sequence;
import org.junit.jupiter.api.Test;

public class UserCacheTest_jMock extends MockObjectTestCase {
    @Test
    void testInnocuousValue() {
        final int key = 42;

        final Storage mockStorage = mock(Storage.class);
        final Logger mockLogger = mock(Logger.class);

        checking(new Expectations() {{
            oneOf (mockStorage).get(key);
            oneOf (mockLogger).log(with(any(String.class)));
        }});

        UserCache sut = new UserCache(mockStorage, mockLogger);
        sut.getAndLog(key);
    }

    @Test void testSequenceOnTwoMocks() {
        final int key = 42;

        final Storage mockStorage = mock(Storage.class);
        final Logger mockLogger = mock(Logger.class);
        final Sequence getSequence = sequence("get");

        checking(new Expectations() {{
            oneOf (mockStorage).get(key);
            inSequence(getSequence);
            oneOf (mockLogger).log(with(any(String.class)));
            inSequence(getSequence);
        }});

        UserCache sut = new UserCache(mockStorage, mockLogger);
        sut.getAndLog(key);
    }
}
