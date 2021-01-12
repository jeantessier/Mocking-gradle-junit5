import junit.framework.TestCase;
import static org.easymock.EasyMock.*;

public class FuzzyParams_EasyMock extends TestCase {
    public void testSimple() {
        int intValue = 42;
        String textValue = "forty-two";

        SimpleInterface mockSimple = createMock(SimpleInterface.class);
        mockSimple.twoParamMethod(gt(40), find("two"));
        replay(mockSimple);

        Client sut = new Client(mockSimple);
        sut.callTwoParams(intValue, textValue);

        verify(mockSimple);
    }
}
