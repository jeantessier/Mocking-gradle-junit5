import org.jmock.Expectations;
import org.jmock.Sequence;
import org.jmock.junit5.JUnit5Mockery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

public class CacheTest_jMock {
    @RegisterExtension
    JUnit5Mockery context = new JUnit5Mockery();
    
    @Test
    void testMethodWithReturnValue() {
        final int expectedValue = 42;

        final Map<Integer, String> mockStorage = context.mock(Map.class);
        
        context.checking(new Expectations() {{
            oneOf (mockStorage).size();
            will(returnValue(expectedValue));
        }});

        Cache sut = new Cache(mockStorage);
        int actualValue = sut.size();
        assertSame(expectedValue, actualValue);
    }

    @Test
    void testMethodWithReturnValueThrowsAnException() {
        final Exception expectedException = new RuntimeException();

        final Map<Integer, String> mockStorage = context.mock(Map.class);

        context.checking(new Expectations() {{
            oneOf (mockStorage).size();
            will(throwException(expectedException));
        }});

        Cache sut = new Cache(mockStorage);
        try {
            sut.size();
            fail("Should have thrown the exception");
        } catch (RuntimeException actualException) {
            assertSame(expectedException, actualException);
        }
    }

    @Test
    void testVoidMethod() {
        final Map<Integer, String> mockStorage = context.mock(Map.class);

        context.checking(new Expectations() {{
            oneOf (mockStorage).clear();
        }});

        Cache sut = new Cache(mockStorage);
        sut.clear();
    }

    @Test
    void testVoidMethodThrowsAnException() {
        final Exception expectedException = new RuntimeException();

        final Map<Integer, String> mockStorage = context.mock(Map.class);

        context.checking(new Expectations() {{
            oneOf (mockStorage).clear();
            will(throwException(expectedException));
        }});

        Cache sut = new Cache(mockStorage);
        try {
            sut.clear();
            fail("Should have thrown the exception");
        } catch (RuntimeException actualException) {
            assertSame(expectedException, actualException);
        }
    }

    @Test
    void testMethodWithParameter() {
        final int key = 42;
        final String expectedValue = "forty-two";

        final Map<Integer, String> mockStorage = context.mock(Map.class);

        context.checking(new Expectations() {{
            oneOf (mockStorage).get(key);
            will(returnValue(expectedValue));
        }});

        Cache sut = new Cache(mockStorage);
        Object actualValue = sut.get(key);
        assertSame(expectedValue, actualValue);
    }

    @Test
    void testExactParams() {
        final int expectedKey = 42;
        final String expectedValue = "forty-two";

        final Map<Integer, String> mockStorage = context.mock(Map.class);

        context.checking(new Expectations() {{
            oneOf (mockStorage).put(expectedKey, expectedValue);
            will(returnValue(true));
        }});

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);
    }

    @Test
    void testFuzzyParams() {
        final int expectedKey = 42;
        final String expectedValue = "forty-two";

        final Map<Integer, String> mockStorage = context.mock(Map.class);

        context.checking(new Expectations() {{
            oneOf (mockStorage).put(with(greaterThan(40)), with(containsString("two")));
            will(returnValue(true));
        }});

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);
    }

    @Test
    void testIgnoreReturnValue() {
        final int expectedKey = 42;
        final String expectedValue = "forty-two";

        final Map<Integer, String> mockStorage = context.mock(Map.class);

        context.checking(new Expectations() {{
            oneOf (mockStorage).put(expectedKey, expectedValue);
        }});

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);
    }

    @Test
    void testIgnoreMethodCall() {
        final Map<Integer, String> mockStorage = context.mock(Map.class);

        context.checking(new Expectations() {{
            oneOf (mockStorage).clear();
            ignoring (mockStorage).size();
            will(returnValue(42));
        }});

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();
    }

    @Test
    void testIgnoreObject() {
        final Map<Integer, String> mockStorage = context.mock(Map.class);

        context.checking(new Expectations() {{
            ignoring (mockStorage);
        }});

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();
    }

    @Test
    void testMultipleCalls() {
        final Map<Integer, String> mockStorage = context.mock(Map.class);

        context.checking(new Expectations() {{
            exactly(2).of (mockStorage).size();
            will(returnValue(42));
            oneOf (mockStorage).clear();
        }});

        Cache sut = new Cache(mockStorage);
        sut.conditionalLogAndClear();
    }

    @Test
    void testSequenceOnOneMock() {
        final Map<Integer, String> mockStorage = context.mock(Map.class);
        final Sequence clearSequence = context.sequence("clear");

        context.checking(new Expectations() {{
            oneOf (mockStorage).size();
            will(returnValue(42));
            inSequence(clearSequence);
            oneOf (mockStorage).clear();
            inSequence(clearSequence);
        }});

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();
    }
}
