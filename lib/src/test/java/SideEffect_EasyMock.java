import org.easymock.IAnswer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.getCurrentArguments;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SideEffect_EasyMock {
    @Test
    void testSideEffect() {
        Populator mockPopulator = createMock(Populator.class);
        mockPopulator.populate(isA(List.class));
        expectLastCall().andAnswer(addToListParameter(0, new Object()));
        replay(mockPopulator);

        Client sut = new Client(mockPopulator);
        int actualValue = sut.callPopulateAndReturnSize();
        assertTrue(actualValue > 0);

        verify(mockPopulator);
    }

    private IAnswer addToListParameter(final int i, final Object object) {
        return new IAnswer() {
            public Object answer() throws Throwable {
                ((List) getCurrentArguments()[i]).add(object);
                return null;
            }
        };
    }
}
