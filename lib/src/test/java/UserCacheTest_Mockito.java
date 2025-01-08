import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserCacheTest_Mockito {
    @Test
    void testInnocuousValue() {
        int key = 42;

        Storage mockStorage = mock(Storage.class);
        Logger mockLogger = mock(Logger.class);

        UserCache sut = new UserCache(mockStorage, mockLogger);
        sut.getAndLog(key);

        verify(mockStorage).get(key);
        verify(mockLogger).log(anyString());
    }

    @Test void testSequenceOnTwoMocks() {
        int key = 42;

        Storage mockStorage = mock(Storage.class);
        Logger mockLogger = mock(Logger.class);
        InOrder inOrder = inOrder(mockStorage, mockLogger);

        UserCache sut = new UserCache(mockStorage, mockLogger);
        sut.getAndLog(key);

        inOrder.verify(mockStorage).get(key);
        inOrder.verify(mockLogger).log(anyString());
    }
}
