import java.util.List;
import java.util.ArrayList;

public class Client {
    private final SimpleInterface simple;
    private final SomeOtherInterface someOther;
    private final Populator populator;

    public Client(SimpleInterface simple) {
        this(simple, null, null);
    }

    public Client(SimpleInterface simple, SomeOtherInterface someOther) {
        this(simple, someOther, null);
    }

    public Client(Populator populator) {
        this(null, null, populator);
    }

    private Client(SimpleInterface simple, SomeOtherInterface someOther, Populator populator) {
        this.simple = simple;
        this.someOther = someOther;
        this.populator = populator;
    }

    public int callSimple() {
        return simple.simpleMethod();
    }

    public int callSimpleTwice() {
        callSimple();
        return callSimple();
    }

    public int callSimpleAndIrrelevant() {
        simple.irrelevantMethod();
        return simple.simpleMethod();
    }

    public int callSimpleAndSomeOther() {
        someOther.someOtherMethod();
        return simple.simpleMethod();
    }

    public void callTwoParams(int i, String s) {
        simple.twoParamMethod(i, s);
    }

    public int callPopulateAndReturnSize() {
        List list = new ArrayList();
        populator.populate(list);
        return list.size();
    }
}
