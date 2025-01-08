import org.junit.jupiter.api.Test;
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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

public class CacheTest_Mockito {
    @Test
    void testMethodWithReturnValue() {
        int expectedValue = 42;

        Map<Integer, String> mockStorage = mock(Map.class);
        when(mockStorage.size()).thenReturn(expectedValue);

        Cache sut = new Cache(mockStorage);
        int actualValue = sut.size();
        assertSame(expectedValue, actualValue);

        verify(mockStorage).size();
    }

    @Test
    void testMethodWithReturnValueThrowsAnException() {
        Exception expectedException = new RuntimeException();

        Map<Integer, String> mockStorage = mock(Map.class);
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

    @Test
    void testVoidMethod() {
        Map<Integer, String> mockStorage = mock(Map.class);

        Cache sut = new Cache(mockStorage);
        sut.clear();

        verify(mockStorage).clear();
    }

    @Test
    void testVoidMethodThrowsAnException() {
        Exception expectedException = new RuntimeException();

        Map<Integer, String> mockStorage = mock(Map.class);
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

    @Test
    void testMethodWithParameter() {
        int key = 42;
        String expectedValue = "forty-two";

        Map<Integer, String> mockStorage = mock(Map.class);
        when(mockStorage.get(key)).thenReturn(expectedValue);

        Cache sut = new Cache(mockStorage);
        Object actualValue = sut.get(key);
        assertSame(expectedValue, actualValue);

        verify(mockStorage).get(key);
    }

    @Test
    void testExactParams() {
        int expectedKey = 42;
        String expectedValue = "forty-two";

        Map<Integer, String> mockStorage = mock(Map.class);

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);

        verify(mockStorage).put(expectedKey, expectedValue);
    }

    @Test
    void testFuzzyParams() {
        int expectedKey = 42;
        String expectedValue = "forty-two";

        Map<Integer, String> mockStorage = mock(Map.class);

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);

        verify(mockStorage).put(geq(40), contains("two"));
    }

    @Test
    void testIgnoreReturnValue() {
        int expectedKey = 42;
        String expectedValue = "forty-two";

        Map<Integer, String> mockStorage = mock(Map.class);

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);

        verify(mockStorage).put(expectedKey, expectedValue);
    }

    @Test
    void testIgnoreMethodCall() {
        Map<Integer, String> mockStorage = mock(Map.class);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();

        verify(mockStorage).clear();
    }

    @Test
    void testIgnoreObject() {
        Map<Integer, String> mockStorage = mock(Map.class);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();
    }

    @Test
    void testMultipleCalls() {
        Map<Integer, String> mockStorage = mock(Map.class);
        when(mockStorage.size()).thenReturn(42);

        Cache sut = new Cache(mockStorage);
        sut.conditionalLogAndClear();

        verify(mockStorage, times(2)).size();
        verify(mockStorage).clear();
    }

    @Test
    void testSequenceOnOneMock() {
        Map<Integer, String> mockStorage = mock(Map.class);
        when(mockStorage.size()).thenReturn(42);
        InOrder inOrder = inOrder(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();

        inOrder.verify(mockStorage).size();
        inOrder.verify(mockStorage).clear();
    }
}
