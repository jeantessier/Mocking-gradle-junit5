import junit.framework.TestCase;
import org.mockito.InOrder;

import java.util.Map;

import static org.mockito.AdditionalMatchers.geq;
import static org.mockito.Mockito.contains;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CacheTest_Mockito extends TestCase {
    public void testMethodWithReturnValue() {
        int expectedValue = 42;

        Map mockStorage = mock(Map.class);
        when(mockStorage.size()).thenReturn(expectedValue);

        Cache sut = new Cache(mockStorage);
        int actualValue = sut.size();
        assertSame(expectedValue, actualValue);

        verify(mockStorage).size();
    }

    public void testMethodWithReturnValueThrowsAnException() {
        Exception expectedException = new RuntimeException();

        Map mockStorage = mock(Map.class);
        when(mockStorage.size()).thenThrow(expectedException);

        Cache sut = new Cache(mockStorage);
        try {
            sut.size();
            fail("Should have thrown the exception");
        } catch (RuntimeException actualException) {
            assertSame(expectedException, actualException);
        }

        verify(mockStorage).size();
    }

    public void testVoidMethod() {
        Map mockStorage = mock(Map.class);

        Cache sut = new Cache(mockStorage);
        sut.clear();

        verify(mockStorage).clear();
    }

    public void testVoidMethodThrowsAnException() {
        Exception expectedException = new RuntimeException();

        Map mockStorage = mock(Map.class);
        doThrow(expectedException).when(mockStorage).clear();

        Cache sut = new Cache(mockStorage);
        try {
            sut.clear();
            fail("Should have thrown the exception");
        } catch (RuntimeException actualException) {
            assertSame(expectedException, actualException);
        }

        verify(mockStorage).clear();
    }

    public void testMethodWithParameter() {
        int key = 42;
        String expectedValue = "forty-two";

        Map mockStorage = mock(Map.class);
        when(mockStorage.get(key)).thenReturn(expectedValue);

        Cache sut = new Cache(mockStorage);
        Object actualValue = sut.get(key);
        assertSame(expectedValue, actualValue);

        verify(mockStorage).get(key);
    }

    public void testExactParams() {
        int expectedKey = 42;
        String expectedValue = "forty-two";

        Map mockStorage = mock(Map.class);

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);

        verify(mockStorage).put(expectedKey, expectedValue);
    }

    public void testFuzzyParams() {
        int expectedKey = 42;
        String expectedValue = "forty-two";

        Map mockStorage = mock(Map.class);

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);

        verify(mockStorage).put(geq(40), contains("two"));
    }

    public void testIgnoreReturnValue() {
        int expectedKey = 42;
        String expectedValue = "forty-two";

        Map mockStorage = mock(Map.class);

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);

        verify(mockStorage).put(expectedKey, expectedValue);
    }

    public void testIgnoreMethodCall() {
        Map mockStorage = mock(Map.class);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();

        verify(mockStorage).clear();
    }

    public void testIgnoreObject() {
        Map mockStorage = mock(Map.class);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();
    }

    public void testMultipleCalls() {
        Map mockStorage = mock(Map.class);
        when(mockStorage.size()).thenReturn(42);

        Cache sut = new Cache(mockStorage);
        sut.conditionalLogAndClear();

        verify(mockStorage, times(2)).size();
        verify(mockStorage).clear();
    }

    public void testSequenceOnOneMock() {
        Map mockStorage = mock(Map.class);
        when(mockStorage.size()).thenReturn(42);
        InOrder inOrder = inOrder(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();

        inOrder.verify(mockStorage).size();
        inOrder.verify(mockStorage).clear();
    }
}
