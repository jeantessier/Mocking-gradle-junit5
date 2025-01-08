import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import java.util.Map;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.partialMockBuilder;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

@Disabled("EasyMock doesn't mock classes")
public class PartialMocking_EasyMock {
    @Test
    void testPartialMocking() {
        Map<Integer, String> mockStorage = createMock(Map.class);
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
