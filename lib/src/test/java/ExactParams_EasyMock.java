import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;

public class ExactParams_EasyMock {
    @Test
    void testSimple() {
        int intValue = 42;
        String textValue = "forty-two";

        SimpleInterface mockSimple = createMock(SimpleInterface.class);
        mockSimple.twoParamMethod(intValue, textValue);
        replay(mockSimple);

        Client sut = new Client(mockSimple);
        sut.callTwoParams(intValue, textValue);

        verify(mockSimple);
    }
}
