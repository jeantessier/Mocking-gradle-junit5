import junit.framework.TestCase;

import java.lang.reflect.Method;
import java.util.Map;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.partialMockBuilder;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class PartialMocking_EasyMock extends TestCase {
    public void testPartialMocking() throws Exception {
        Map mockStorage = createMock(Map.class);
        mockStorage.clear();
        expect(mockStorage.size()).andStubReturn(42);

        Cache sut = partialMockBuilder(Cache.class).addMockedMethod("log", String.class).createMock();
        sut.log(isA(String.class));

        replay(mockStorage, sut);

        sut.setUnderlyingStorage(mockStorage);
        sut.logAndClear();

        verify(mockStorage);
    }
}
