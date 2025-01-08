import org.junit.jupiter.api.*;

import java.util.Map;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.find;
import static org.easymock.EasyMock.gt;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

public class CacheTest_EasyMock {
    @Test
    void testMethodWithReturnValue() {
        int expectedValue = 42;

        Map<Integer, String> mockStorage = createMock(Map.class);
        expect(mockStorage.size()).andReturn(expectedValue);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        Object actualValue = sut.size();
        assertSame(expectedValue, actualValue);

        verify(mockStorage);
    }

    @Test
    void testMethodWithReturnValueThrowsAnException() {
        Exception expectedException = new RuntimeException();

        Map<Integer, String> mockStorage = createMock(Map.class);
        expect(mockStorage.size()).andThrow(expectedException);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        try {
            sut.size();
            fail("Should have thrown the exception");
        } catch (RuntimeException actualException) {
            assertSame(expectedException, actualException);
        }

        verify(mockStorage);
    }

    @Test
    void testVoidMethod() {
        Map<Integer, String> mockStorage = createMock(Map.class);
        mockStorage.clear();
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.clear();

        verify(mockStorage);
    }

    @Test
    void testVoidMethodThrowsAnException() {
        Exception expectedException = new RuntimeException();

        Map<Integer, String> mockStorage = createMock(Map.class);
        mockStorage.clear();
        expectLastCall().andThrow(expectedException);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        try {
            sut.clear();
            fail("Should have thrown the exception");
        } catch (RuntimeException actualException) {
            assertSame(expectedException, actualException);
        }

        verify(mockStorage);
    }

    @Test
    void testMethodWithParameter() {
        int key = 42;
        String expectedValue = "forty-two";

        Map<Integer, String> mockStorage = createMock(Map.class);
        expect(mockStorage.get(key)).andReturn(expectedValue);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        Object actualValue = sut.get(key);
        assertSame(expectedValue, actualValue);

        verify(mockStorage);
    }

    @Test
    void testExactParams() {
        int expectedKey = 42;
        String expectedValue = "forty-two";

        Map<Integer, String> mockStorage = createMock(Map.class);
        expect(mockStorage.put(expectedKey, expectedValue)).andReturn(null);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);

        verify(mockStorage);
    }

    @Test
    void testFuzzyParams() {
        int expectedKey = 42;
        String expectedValue = "forty-two";

        Map<Integer, String> mockStorage = createMock(Map.class);
        expect(mockStorage.put(gt(40), find("two"))).andReturn(null);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);

        verify(mockStorage);
    }

    @Test
    void testIgnoreMethodCall() {
        Map<Integer, String> mockStorage = createMock(Map.class);
        mockStorage.clear();
        expect(mockStorage.size()).andStubReturn(42);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();

        verify(mockStorage);
    }

    @Test
    void testIgnoreObject() {
        Map<Integer, String> mockStorage = createNiceMock(Map.class);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();

        verify(mockStorage);
    }

    @Test
    void testMultipleCalls() {
        Map<Integer, String> mockStorage = createMock(Map.class);
        expect(mockStorage.size()).andReturn(42).times(2);
        mockStorage.clear();
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.conditionalLogAndClear();

        verify(mockStorage);
    }

    @Test
    void testSequenceOnOneMock() {
        Map<Integer, String> mockStorage = createStrictMock(Map.class);
        expect(mockStorage.size()).andReturn(42);
        mockStorage.clear();
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();

        verify(mockStorage);
    }

    @Test
    void testPartialMock() {
        Map<Integer, String> mockStorage = createMock(Map.class);
        mockStorage.clear();
        expect(mockStorage.size()).andStubReturn(42);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();

        verify(mockStorage);
    }
}
