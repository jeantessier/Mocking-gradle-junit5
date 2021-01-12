import org.jmock.Expectations;
import org.jmock.api.Action;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;

import java.util.List;

public class SideEffect_jMock extends MockObjectTestCase {
    public void testSideEffect() {
        final Populator mockPopulator = mock(Populator.class);

        checking(new Expectations() {{
            oneOf (mockPopulator).populate(with(any(List.class)));
            will(addToListParameter(0, new Object()));
        }});

        Client sut = new Client(mockPopulator);
        int actualSize = sut.callPopulateAndReturnSize();
        assertTrue(actualSize > 0);
    }

    private Action addToListParameter(final int i, final Object object) {
        return new CustomAction("Add a value to a list") {
            public Object invoke(Invocation invocation) {
                ((List) invocation.getParameter(i)).add(object);
                return null;
            }
        };
    }
}
