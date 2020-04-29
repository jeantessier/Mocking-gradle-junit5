import org.jmock.Expectations;
import org.jmock.Sequence;

import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;

public class CacheTest_jMock extends MockObjectTestCase {
    public void testMethodWithReturnValue() {
        final int expectedValue = 42;

        final Map mockStorage = mock(Map.class);
        
        checking(new Expectations() {{
            oneOf (mockStorage).size();
            will(returnValue(expectedValue));
        }});

        Cache sut = new Cache(mockStorage);
        int actualValue = sut.size();
        assertSame(expectedValue, actualValue);
    }

    public void testMethodWithReturnValueThrowsAnException() {
        final Exception expectedException = new RuntimeException();

        final Map mockStorage = mock(Map.class);

        checking(new Expectations() {{
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

    public void testVoidMethod() {
        final Map mockStorage = mock(Map.class);

        checking(new Expectations() {{
            oneOf (mockStorage).clear();
        }});

        Cache sut = new Cache(mockStorage);
        sut.clear();
    }

    public void testVoidMethodThrowsAnException() {
        final Exception expectedException = new RuntimeException();

        final Map mockStorage = mock(Map.class);

        checking(new Expectations() {{
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

    public void testMethodWithParameter() {
        final int key = 42;
        final String expectedValue = "forty-two";

        final Map mockStorage = mock(Map.class);

        checking(new Expectations() {{
            oneOf (mockStorage).get(key);
            will(returnValue(expectedValue));
        }});

        Cache sut = new Cache(mockStorage);
        Object actualValue = sut.get(key);
        assertSame(expectedValue, actualValue);
    }

    public void testExactParams() {
        final int expectedKey = 42;
        final String expectedValue = "forty-two";

        final Map mockStorage = mock(Map.class);

        checking(new Expectations() {{
            oneOf (mockStorage).put(expectedKey, expectedValue);
            will(returnValue(true));
        }});

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);
    }

    public void testFuzzyParams() {
        final int expectedKey = 42;
        final String expectedValue = "forty-two";

        final Map mockStorage = mock(Map.class);

        checking(new Expectations() {{
            oneOf (mockStorage).put(with(greaterThan(40)), with(containsString("two")));
            will(returnValue(true));
        }});

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);
    }

    public void testIgnoreReturnValue() {
        final int expectedKey = 42;
        final String expectedValue = "forty-two";

        final Map mockStorage = mock(Map.class);

        checking(new Expectations() {{
            oneOf (mockStorage).put(expectedKey, expectedValue);
        }});

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);
    }

    public void testIgnoreMethodCall() {
        final Map mockStorage = mock(Map.class);

        checking(new Expectations() {{
            oneOf (mockStorage).clear();
            ignoring (mockStorage).size();
            will(returnValue(42));
        }});

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();
    }

    public void testIgnoreObject() {
        final Map mockStorage = mock(Map.class);

        checking(new Expectations() {{
            ignoring (mockStorage);
        }});

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();
    }

    public void testMultipleCalls() {
        final Map mockStorage = mock(Map.class);

        checking(new Expectations() {{
            exactly(2).of (mockStorage).size();
            will(returnValue(42));
            oneOf (mockStorage).clear();
        }});

        Cache sut = new Cache(mockStorage);
        sut.conditionalLogAndClear();
    }

    public void testSequenceOnOneMock() {
        final Map mockStorage = mock(Map.class);
        final Sequence clearSequence = sequence("clear");

        checking(new Expectations() {{
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
