import junit.framework.TestCase;

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

public class CacheTest_EasyMock extends TestCase {
    public void testMethodWithReturnValue() {
        int expectedValue = 42;

        Map mockStorage = createMock(Map.class);
        expect(mockStorage.size()).andReturn(expectedValue);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        Object actualValue = sut.size();
        assertSame(expectedValue, actualValue);

        verify(mockStorage);
    }

    public void testMethodWithReturnValueThrowsAnException() {
        Exception expectedException = new RuntimeException();

        Map mockStorage = createMock(Map.class);
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

    public void testVoidMethod() {
        Map mockStorage = createMock(Map.class);
        mockStorage.clear();
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.clear();

        verify(mockStorage);
    }

    public void testVoidMethodThrowsAnException() {
        Exception expectedException = new RuntimeException();

        Map mockStorage = createMock(Map.class);
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

    public void testMethodWithParameter() {
        int key = 42;
        String expectedValue = "forty-two";

        Map mockStorage = createMock(Map.class);
        expect(mockStorage.get(key)).andReturn(expectedValue);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        Object actualValue = sut.get(key);
        assertSame(expectedValue, actualValue);

        verify(mockStorage);
    }

    public void testExactParams() {
        int expectedKey = 42;
        String expectedValue = "forty-two";

        Map mockStorage = createMock(Map.class);
        expect(mockStorage.put(expectedKey, expectedValue)).andReturn(true);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);

        verify(mockStorage);
    }

    public void testFuzzyParams() {
        int expectedKey = 42;
        String expectedValue = "forty-two";

        Map mockStorage = createMock(Map.class);
        expect(mockStorage.put(gt(40), find("two"))).andReturn(true);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.add(expectedKey, expectedValue);

        verify(mockStorage);
    }

    public void testIgnoreMethodCall() {
        Map mockStorage = createMock(Map.class);
        mockStorage.clear();
        expect(mockStorage.size()).andStubReturn(42);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();

        verify(mockStorage);
    }

    public void testIgnoreObject() {
        Map mockStorage = createNiceMock(Map.class);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();

        verify(mockStorage);
    }

    public void testMultipleCalls() {
        Map mockStorage = createMock(Map.class);
        expect(mockStorage.size()).andReturn(42).times(2);
        mockStorage.clear();
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.conditionalLogAndClear();

        verify(mockStorage);
    }

    public void testSequenceOnOneMock() {
        Map mockStorage = createStrictMock(Map.class);
        expect(mockStorage.size()).andReturn(42);
        mockStorage.clear();
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();

        verify(mockStorage);
    }

    public void testPartialMock() {
        Map mockStorage = createMock(Map.class);
        mockStorage.clear();
        expect(mockStorage.size()).andStubReturn(42);
        replay(mockStorage);

        Cache sut = new Cache(mockStorage);
        sut.logAndClear();

        verify(mockStorage);
    }
}
